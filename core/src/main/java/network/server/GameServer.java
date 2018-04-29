package network.server;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import javax.swing.*;

import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.kryonet.Server;
import network.database.HibernateSessionFactory;
import network.database.entity.AccountEntity;
import network.manager.NetworkManager;
import com.esotericsoftware.minlog.Log;
import network.manager.PlayerCombatInfo;

import static network.database.DBConnector.fetchCombatSetup;
import static network.database.DBConnector.fetchUserCredentials;

// TODO: Create Dedicated Server module
public class GameServer {
    private Server server;
    private List<GameConnection> connectionPool = new ArrayList<>();
    private HashMap<String, Integer> loggedIn = new HashMap<>();
    private PlayerPool playerPool = new PlayerPool(this);

    public GameServer() throws IOException {
        server = new Server() {
            protected Connection newConnection() {
                // By providing our own connection implementation, we can store per
                // connection state without a connection ID to state look up.
                GameConnection con = new GameConnection();
                connectionPool.add(con);
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

                    if (!isValid(credentials.getUsername()) || !isValid(credentials.getPassword())) {
                        Log.error("[Log] Invalid credentials.");
                        server.sendToTCP(connection.getID(), response);
                        return;
                    }

                    Log.debug("[Log] GOT CHECK CREDS. USER: " + credentials.getUsername());
                    AccountEntity acc = fetchUserCredentials(credentials.getUsername(), HibernateSessionFactory.getSessionFactory());

                    if (acc == null) {
                        Log.error("[Log] Not registered Username.");
                        response.setUserToken(null);  // OR STRING CODE VALUE?
                        server.sendToTCP(connection.getID(), response);
                        return;
                    }
                    connection.username = credentials.getUsername();
                    if (credentials.getPassword().equals(acc.getPwd())) {
                        Log.error("[Log] Password match. OK.");
                        loggedIn.put(connection.userToken, acc.getId());
                        response.setUserToken(connection.userToken);
                        server.sendToTCP(connection.getID(), response);
                    } else {
                        Log.error("[Log] Wrong Password.");
                        response.setUserToken(null);  // OR STRING CODE VALUE?
                        server.sendToTCP(connection.getID(), response);
                    }
                    return;
                }

                if (object instanceof NetworkManager.JoinBattleRequest) {
                    NetworkManager.JoinBattleRequest request = (NetworkManager.JoinBattleRequest) object;
                    if (!loggedIn.containsKey(request.getUserToken())) {
                        Log.error("[Log trace] Join Battle Request from not logged user.");
                        return;
                    }
                    playerPool.addPlayer(connection);
                    return;
                }

                // TODO: Damage
                if (object instanceof NetworkManager.CheckCredentialRequest) {
                    /**
                     connectionPool + 1 player;
                     if == 2 -> draw screen
                     send first about second. second about first.
                     server.sendToAllExceptTCP(connection.getID(), chatMessage);
                     */

//                    EnterRoomWithSetup setup = (EnterRoomWithSetup) object;
//                    combatSetupsInARoom.add(setup);
//                    if (connectionPool.size() > 1) {
//                        server.sendToTCP(connectionPool.get(0).getID(), combatSetupsInARoom.get(1));
//                        server.sendToTCP(connectionPool.get(1).getID(), combatSetupsInARoom.get(0));
//                    }
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

    /**
     * Fetch players data from database and send to players.
     * @param room where 2 players are
     */
    void sendBattleBeginResponse(Room room) {
        Log.debug("[Log] SENDING BEGIN BATTLE RESPONSE.");

        GameConnection con1 = room.getFirstPlayerConnection();
        GameConnection con2 = room.getSecondPlayerConnection();

        int playerId1 = loggedIn.get(con1.userToken);
        Log.debug("[Log] PlayerId: " + playerId1);
        room.setFirstPlayerCombatInfo(
                new PlayerCombatInfo(
                        con1.username,
                        fetchCombatSetup(playerId1, HibernateSessionFactory.getSessionFactory())));

        int playerId2 = loggedIn.get(con2.userToken);
        Log.debug("[Log] PlayerId: " + playerId2);
        room.setSecondPlayerCombatInfo(
                new PlayerCombatInfo(
                        con2.username,
                        fetchCombatSetup(playerId2, HibernateSessionFactory.getSessionFactory())));


        NetworkManager.BeginBattleResponse response = new NetworkManager.BeginBattleResponse();

        response.setPlayerCombatInfo(room.getFirstPlayerCombatInfo());
        response.setOpponentCombatInfo(room.getSecondPlayerCombatInfo());
        Log.debug("[Log] SENDING TO 1ST PLAYER." + room.getFirstPlayerCombatInfo());
        server.sendToTCP(con1.getID(), response);

        response = new NetworkManager.BeginBattleResponse();
        response.setPlayerCombatInfo(room.getSecondPlayerCombatInfo());
        response.setOpponentCombatInfo(room.getFirstPlayerCombatInfo());
        Log.debug("[Log] SENDING TO 2ND PLAYER." + room.getFirstPlayerCombatInfo());
        server.sendToTCP(con2.getID(), response);
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
        public String username;
    }

    public static void main(String[] args) throws IOException {
        Log.set(Log.LEVEL_DEBUG);
        new GameServer();
    }
}