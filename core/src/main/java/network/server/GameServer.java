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

                if (object instanceof NetworkManager.GetCombatSetupRequest) {
                    NetworkManager.GetCombatSetupRequest request = (NetworkManager.GetCombatSetupRequest) object;
                    if (!loggedIn.containsKey(request.getUserToken())) {
                        Log.error("[Log trace] Get Combat Setup Request from not logged user.");
                        return;
                    }
                    sendGetCombatSetupResponse(connection);
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

                if (object instanceof NetworkManager.DealDamageRequest) {
                    NetworkManager.DealDamageRequest request = (NetworkManager.DealDamageRequest) object;
//                    if (!loggedIn.containsKey(request.getUserToken())) {
//                        Log.error("[Log trace] Deal Damage Request from not logged user.");
//                        return;
//                    }
                    processDamageRequest(connection, request);
                }

                // TODO: turns
                if (object instanceof NetworkManager.TurnEndRequest) {
                    /* I think we don't even need loggedIn check here */
                    manageTurnsForPlayers(connection);
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

    private void manageTurnsForPlayers(GameConnection con) {
        try {
            Room room = playerPool.findRoomByPlayerConnection(con);
            if (!room.isPlayersTurn(con)) {
                Log.error("[CHEATS!?] End Turn Request from player whose turn is finished.");
                return;
            }

            NetworkManager.PlayerTurnResponse endTurnResponse = new NetworkManager.PlayerTurnResponse();
            endTurnResponse.setYourTurn(false);

            Log.debug("[Turn] Ends turn server side and then sends end turn notification to the users to rotate");
            room.getCombatLogic().endTurn();
            server.sendToTCP(con.getID(), endTurnResponse);


            NetworkManager.PlayerTurnResponse startTurnResponse = new NetworkManager.PlayerTurnResponse();
            endTurnResponse.setYourTurn(true);

            server.sendToTCP(room.getOpponentConnection(con).getID(), startTurnResponse);
        } catch (NetworkException e) {
            e.printStackTrace();
        }
    }

    /**
     * Find room where requesting player is.
     * Get room CombatLogicController.
     * Calculate Damage.
     * Send Response.
     * @param con
     */
    private void processDamageRequest(GameConnection con, NetworkManager.DealDamageRequest request) {
        try {
            Room room = playerPool.findRoomByPlayerConnection(con);
//            if (!room.isPlayersTurn(con)) {
//                Log.error("[CHEATS!?] Damage Deal Request from player whose turn is finished.");
//                return;
//            }
            double damage = room.getCombatLogic()
                    .calculateDamage(request.getCastedSpellType(), request.getDealerSlotId(), request.getTargetSlotId());


            NetworkManager.DealDamageResponse casterResponse = new NetworkManager.DealDamageResponse();
            casterResponse.setDealtDamage(damage);
            casterResponse.setDealerSlotId(request.getDealerSlotId());
            casterResponse.setTargetSlotId(request.getTargetSlotId());
            server.sendToTCP(con.getID(), casterResponse);

            NetworkManager.DealDamageResponse targetResponse = new NetworkManager.DealDamageResponse();
            targetResponse.setDealtDamage(damage);
            targetResponse.setDealerSlotId(request.getDealerSlotId() - 3);
            targetResponse.setTargetSlotId(request.getTargetSlotId() + 3);
            server.sendToTCP(room.getOpponentConnection(con).getID(), targetResponse);
        } catch (NetworkException e) {
            Log.error("[Log] Not existing room.");
        }
    }

    private void sendGetCombatSetupResponse(GameConnection connection) {

        NetworkManager.GetCombatSetupResponse response = new NetworkManager.GetCombatSetupResponse();
        int playerId = loggedIn.get(connection.userToken);

        Log.debug("[Log] Player requesting setup Id: " + playerId);
        response.setPlayerCombatInfo(new PlayerCombatInfo(
                connection.username,
                fetchCombatSetup(playerId, HibernateSessionFactory.getSessionFactory())));
        server.sendToTCP(connection.getID(), response);
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
        /* Player created room has a right for the first turn */
        NetworkManager.PlayerTurnResponse firstTurnResponse = new NetworkManager.PlayerTurnResponse();
        firstTurnResponse.setYourTurn(true);
        room.getCombatLogic().setPlayerOneTurn(true);
        server.sendToTCP(con1.getID(), firstTurnResponse);

        response = new NetworkManager.BeginBattleResponse();
        response.setPlayerCombatInfo(room.getSecondPlayerCombatInfo());
        response.setOpponentCombatInfo(room.getFirstPlayerCombatInfo());
        Log.debug("[Log] SENDING TO 2ND PLAYER." + room.getFirstPlayerCombatInfo());
        server.sendToTCP(con2.getID(), response);
    }


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