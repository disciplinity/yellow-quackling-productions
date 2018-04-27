package network.client;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import game.MyGdxGame;
import game.screens.ConnectionTestScreen;
import network.manager.NetworkManager.*;
import network.manager.NetworkManager;

import java.io.IOException;


public class GameClient {
    Client client;
    MyGdxGame game;
    String host = "localhost";
    String userToken;

    // TODO: Think about merging this class with main game class (or leave it as it is, because here is where client connection is managed)
    public GameClient() throws IOException {
        client = new Client();
        client.start();
        game = MyGdxGame.getInstance();

        // Register the class to be sent over the network
        NetworkManager.register(client);

        client.addListener(new Listener() {
            public void connected(Connection connection) {
                System.out.println("Connected!");
            }

            public void received(Connection connection, Object object) {

                if (object instanceof SessionTokenResponse) {
                    SessionTokenResponse response = (SessionTokenResponse) object;
                    if (response.getUserToken() == null) {
                        System.err.println("Wrong Password");
                        ((ConnectionTestScreen) MyGdxGame.getInstance().getCurrentScreen()).setAlert("Wrong Password or Username");
                        return;
                    }
                    userToken = response.getUserToken();
                    System.out.println("HELLO THERE!");
                    game.setLobbyScreen();
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
        client.sendTCP(request);
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
