package main.java.network.kryonet.client;

import com.badlogic.gdx.Gdx;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import main.java.game.MyGdxGame;
import main.java.game.models.combat.CombatSetup;
import main.java.network.kryonet.register.NetworkRegister;
import main.java.network.kryonet.register.NetworkRegister.EnterRoomWithSetup;

import java.io.IOException;


public class GameClient {
    Client client;
    MyGdxGame game;

    // TODO: Think about merging this class with main game class (or leave it as it is, because here is where client connection is managed)
    public GameClient(String chosenGlobalSetupId, MyGdxGame game, String host) {
        client = new Client();
        client.start();
        this.game = game;

        // Register the class to be sent over the network
        NetworkRegister.register(client);

        client.addListener(new Listener() {
            public void connected(Connection connection) {
                EnterRoomWithSetup registerCombatGroup = new EnterRoomWithSetup();
                registerCombatGroup.globalSetupId = chosenGlobalSetupId;
                client.sendTCP(registerCombatGroup);
            }

            public void received(Connection connection, Object object) {

                if (object instanceof EnterRoomWithSetup) {
                    EnterRoomWithSetup opponentInRoom = (EnterRoomWithSetup) object;
                    changeToBattleScene(opponentInRoom, chosenGlobalSetupId);
                }
            }

            public void disconnected(Connection connection) {
                // TODO: Return to lobby. Could be implemented only when client will know about the screen.
            }
        });

        try {
            client.connect(5000, host, NetworkRegister.port);
            // Server communication after connection can go here, or in Listener#connected().
        } catch (IOException ex) {
            ex.printStackTrace();
            System.exit(1);
        }
    }

    private void changeToBattleScene(EnterRoomWithSetup opponentInRoom, String chosenGlobalSetupId) {
        Gdx.app.postRunnable(() -> {
            CombatSetup playerCS;
            CombatSetup opponentCS;

            if (chosenGlobalSetupId.equals("1")) {
                System.err.println("here1");
//                playerCS = createCombatGroupExample1();
            } else {
                System.err.println("here2");
//                    playerCS = createCombatGroupExample2();
            }


            if (opponentInRoom.globalSetupId.equals("1")) {
                System.err.println("here3");
//                    opponentCS =createCombatGroupExample1();
            } else {
                System.err.println("here4");
//                    opponentCS = createCombatGroupExample2();
            }

            // TODO: game.setBattleScreen();
//            BattleStageGroup bsg = new BattleStageGroup("fairy-forest.jpg", playerCS, opponentCS);
//            game.setScreen(new CombatScreen(game, bsg));
        });
    }

}