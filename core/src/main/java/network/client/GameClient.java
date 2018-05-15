package network.client;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;
import game.screens.ScreenController;
import network.manager.NetworkManager.*;
import network.manager.NetworkManager;
import network.manager.PlayerCombatInfo;

import java.io.IOException;

/**
 * Game client class manages every action should be performed due to received responses from the server.
 */
public class GameClient {
    private Client client;
    private String host = "localhost";
    private String userToken;


    public GameClient() throws IOException {
        Log.set(Log.LEVEL_DEBUG);
        client = new Client();
        client.start();

        // Register the class to be sent over the network
        NetworkManager.register(client);

        client.addListener(new Listener() {
            public void connected(Connection connection) {
                Log.info("Connected");
            }

            public void received(Connection connection, Object object) {

                if (object instanceof SessionTokenResponse) {
                    SessionTokenResponse response = (SessionTokenResponse) object;
                    userToken = response.getUserToken();
                    if (userToken == null) {
                        Log.error("Wrong Password");
                        ScreenController.setScreenAlert("Invalid Username or Password");
                        return;
                    }
                    Log.debug("HELLO THERE! Sent credentials and setting lobby screen");
                    ScreenController.setLobbyScreen();
                    return;
                }

                if (object instanceof BeginBattleResponse) {
                    BeginBattleResponse response = (BeginBattleResponse) object;
                    Log.debug("[DEBUG] Got Begin Battle Response.");
                    Log.debug("Halo there" + response.getPlayerCombatInfo().toString());
                    Log.debug("Halo there" + response.getOpponentCombatInfo().toString());
                    beginBattle(response.getPlayerCombatInfo(), response.getOpponentCombatInfo());
                }
            }

            public void disconnected(Connection connection) {
                // TODO: System.exit(1);
            }
        });

        client.connect(5000, host, NetworkManager.port);
//        try {
//
//            // Server communication after connection can go here, or in Listener#connected().
//        } catch (IOException ex) {
//            ex.printStackTrace();
//            System.exit(1);
//        }
    }

    private void beginBattle(PlayerCombatInfo player, PlayerCombatInfo opponent) {
        ScreenController.setBattleScreen(player, opponent);
    }


    public void sendCredentials(String username, String password) {
        CheckCredentialRequest request = new CheckCredentialRequest();
        System.out.println(username + " " + password);
        request.setUsername(username);
        request.setPassword(password);
        client.sendTCP(request);
    }

    public void joinBattle() {
        JoinBattleRequest request = new JoinBattleRequest();
        request.setUserToken(userToken);
        Log.debug("[DEBUG] Sent Join Battle Request");
        client.sendTCP(request);
    }

    public void sendDamageRequest(int dealerSlotId, int targetSlotId, int spellId, int damage) {
        DealDamageRequest dealDamageRequest = new DealDamageRequest(dealerSlotId, targetSlotId, spellId, damage);
        client.sendTCP(dealDamageRequest);
    }



    // TODO: Drawing new info at the screen must not be here!
//    private void changeToBattleScene(EnterRoomWithSetup opponentInRoom, String chosenGlobalSetupId) {
//        Gdx.app.postRunnable(() -> {
//            CombatSetup playerCS;
//            CombatSetup opponentCS;
//
//            if (chosenGlobalSetupId.equals("1")) {
//                System.err.println("here1");
////                playerCS = createCombatGroupExample1();
//            } else {
//                System.err.println("here2");
////                    playerCS = createCombatGroupExample2();
//            }
//
//
//            if (opponentInRoom.globalSetupId.equals("1")) {
//                System.err.println("here3");
////                    opponentCS =createCombatGroupExample1();
//            } else {
//                System.err.println("here4");
////                    opponentCS = createCombatGroupExample2();
//            }
//
//            // TODO: game.setBattleScreen();
////            BattleStageGroup bsg = new BattleStageGroup("fairy-forest.jpg", playerCS, opponentCS);
////            game.setScreen(new CombatScreen(game, bsg));
//        });
//    }

}
