package network.server;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.swing.JFrame;
import javax.swing.JLabel;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import network.database.HibernateSessionFactory;
import network.database.entity.AccountEntity;
import network.manager.NetworkManager;
import com.esotericsoftware.minlog.Log;

import static network.database.DBConnector.fetchUserCredentials;

public class GameServer {
    private Server server;
    // TODO: Create Dedicated Server module
    private List<Connection> connectionRoom = new ArrayList<>();
    private HashMap<String, Integer> loggedIn = new HashMap<>();
//    private List<EnterRoomWithSetup> combatSetupsInARoom = new ArrayList<>();

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

        NetworkManager.register(server);

        // TODO: write log on invalid data
        server.addListener(new Listener() {
            public void received(Connection c, Object object) {
                GameConnection connection = (GameConnection) c;

                if (object instanceof NetworkManager.CheckCredentialRequest) {
                    NetworkManager.CheckCredentialRequest credentials = (NetworkManager.CheckCredentialRequest) object;

                    NetworkManager.SessionTokenResponse response = new NetworkManager.SessionTokenResponse();
                    System.out.println(credentials.getUsername());
                    AccountEntity acc = fetchUserCredentials(credentials.getUsername(), HibernateSessionFactory.getSessionFactory());

                    if (acc == null) {
                        Log.trace("[Log] Wrong Username.");
                        response.setUserToken(null);  // OR STRING CODE VALUE?
                        return;
                    }
                    if (!credentials.getPassword().equals(acc.getPwd())) {
                        System.err.println("Wrong Password");
                        Log.trace("[Log] Wrong Password.");
                        response.setUserToken(null);  // OR STRING CODE VALUE?
                        server.sendToTCP(connection.getID(), response);
                    } else {
                        System.err.println("OK");
                        Log.trace("[Log trace] OK.");
                        Log.info("[Log info] OK.");
                        loggedIn.put(connection.userToken, acc.getId());
                        response.setUserToken(connection.userToken);
                        server.sendToTCP(connection.getID(), response);
                    }
                }

                if (object instanceof NetworkManager.JoinBattleRequest) {
                    NetworkManager.JoinBattleRequest request = (NetworkManager.JoinBattleRequest) object;
                    if (!loggedIn.containsKey(request.getUserToken())) {
                        Log.trace("[Log trace] Join Battle Request from not logged user.");
                        return;
                    }

                }

                if (object instanceof NetworkManager.CheckCredentialRequest) {
                    /**
                     connectionRoom + 1 player;
                     if == 2 -> draw screen
                     send first about second. second about first.
                     server.sendToAllExceptTCP(connection.getID(), chatMessage);
                     */
//                    EnterRoomWithSetup setup = (EnterRoomWithSetup) object;
//                    combatSetupsInARoom.add(setup);
                    if (connectionRoom.size() > 1) {
//                        server.sendToTCP(connectionRoom.get(0).getID(), combatSetupsInARoom.get(1));
//                        server.sendToTCP(connectionRoom.get(1).getID(), combatSetupsInARoom.get(0));
                    }
//                    updateNames();
                }
            }

            private boolean isValid (String value) {
                if (value == null) return false;
                value = value.trim();
                return value.length() != 0;
            }

            public void disconnected(Connection c) {
                GameConnection connection = (GameConnection) c;
                if (connection.userToken != null) {
                    loggedIn.remove(connection.userToken);

//                    RemoveCharacter removeCharacter = new RemoveCharacter();
//                    removeCharacter.id = connection.character.id;
//                    server.sendToAllTCP(removeCharacter);
                }
            }
        });
        server.bind(NetworkManager.port);
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

    /**
     * This holds per connection state.
     */
    static class GameConnection extends Connection {
        public String userToken = UUID.randomUUID().toString();
    }

    public static void main(String[] args) throws IOException {
        Log.set(Log.LEVEL_DEBUG);
        new GameServer();
    }
}