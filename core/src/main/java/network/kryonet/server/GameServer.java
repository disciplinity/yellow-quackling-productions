package network.kryonet.server;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import network.kryonet.register.NetworkRegister;
import com.esotericsoftware.minlog.Log;
import network.kryonet.register.NetworkRegister.*;

public class GameServer {
    Server server;
    // TODO: !!!dedicate server from general package path, even out of core module!!!
    // TODO: Room class. Room list. etc...
    // TODO: LASCIATE OGNI SPERANZA, VOI CH'ENTRATE
    private List<Connection> connectionRoom = new ArrayList<>();
    private List<EnterRoomWithSetup> combatSetupsInARoom = new ArrayList<>();

    public GameServer() throws IOException {
        server = new Server() {
            protected Connection newConnection() {
                // By providing our own connection implementation, we can store per
                // connection state without a connection ID to state look up.
                Connection con = new GameConnection();
                connectionRoom.add(con);
                return con;
            }
        };

        NetworkRegister.register(server);

        server.addListener(new Listener() {
            public void received(Connection c, Object object) {
                // We know all connections for this server are actually GameConnections.
                GameConnection connection = (GameConnection) c;
                if (object instanceof EnterRoomWithSetup) {
                    /**
                     connectionRoom + 1 player;
                     if == 2 -> draw screen
                     send first about second. second about first.
                     server.sendToAllExceptTCP(connection.getID(), chatMessage);
                     */
                    EnterRoomWithSetup setup = (EnterRoomWithSetup) object;
                    combatSetupsInARoom.add(setup);
                    if (connectionRoom.size() > 1) {
                        server.sendToTCP(connectionRoom.get(0).getID(), combatSetupsInARoom.get(1));
                        server.sendToTCP(connectionRoom.get(1).getID(), combatSetupsInARoom.get(0));
                    }
//                    updateNames();
                }
            }

            public void disconnected(Connection c) {
                GameConnection connection = (GameConnection) c;
                if (connection.name != null) {
                    // Announce to everyone that someone (with a registered name) has left.
//                    ChatMessage chatMessage = new ChatMessage();
//                    chatMessage.text = connection.name + " disconnected.";
//                    server.sendToAllTCP(chatMessage);
//                    updateNames();
                }
            }
        });
        server.bind(NetworkRegister.port);
        server.start();

        // Open a window to provide an easy way to stop the server.
        JFrame frame = new JFrame("Game Server");
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.addWindowListener(new WindowAdapter() {
            public void windowClosed(WindowEvent evt) {
                server.stop();
            }
        });
        frame.getContentPane().add(new JLabel("Close to stop the server."));
        frame.setSize(320, 200);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

//    void updateNames () {
//        // Collect the names for each connection.
//        Connection[] connections = server.getConnections();
//        ArrayList names = new ArrayList(connections.length);
//        for (int i = connections.length - 1; i >= 0; i--) {
//            GameConnection connection = (GameConnection)connections[i];
//            names.add(connection.name);
//        }
//        // Send the names to everyone.
//        UpdateNames updateNames = new UpdateNames();
//        updateNames.names = (String[])names.toArray(new String[names.size()]);
//        server.sendToAllTCP(updateNames);
//    }

    // This holds per connection state.
    // TODO: some useful info could be added (setup id, for example (or player id for future database requests by pid))
    static class GameConnection extends Connection {
        public String name;
    }

    public static void main(String[] args) throws IOException {
        Log.set(Log.LEVEL_DEBUG);
        new GameServer();
    }
}