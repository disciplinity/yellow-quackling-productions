package network.kryonet.client_chat;

import battle.BattleStageGroup;
import com.badlogic.gdx.Gdx;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import game.MyGdxGame;
import models.CombatSetup;
import network.kryonet.register.NetworkRegister;
import network.kryonet.register.NetworkRegister.EnterRoomWithSetup;
import screens.InBattleScreen;

import java.io.IOException;

import static factory.TestFactories.createCombatGroupExample1;
import static factory.TestFactories.createCombatGroupExample2;

public class GameClient {
    Client client;
    String name;
    MyGdxGame game;

    public GameClient(String chosenGlobalSetupId, MyGdxGame game, String host) {
        client = new Client();
        client.start();
        this.game = game;

        // Register the class to be sent over the network
        NetworkRegister.register(client);


        client.addListener(new Listener() {
            public void connected (Connection connection) {
                NetworkRegister.EnterRoomWithSetup registerCombatGroup = new NetworkRegister.EnterRoomWithSetup();
                registerCombatGroup.globalSetupId = chosenGlobalSetupId;
                client.sendTCP(registerCombatGroup);
            }

            public void received (Connection connection, Object object) {

                if (object instanceof EnterRoomWithSetup) {
                    EnterRoomWithSetup opponentInRoom = (EnterRoomWithSetup)object;
                    setAndFillBattleScene(opponentInRoom, chosenGlobalSetupId);
                }
            }

            public void disconnected (Connection connection) {
                // TODO: Return to lobby. Could be implemented only when client will know about the screen.
            }
        });


        // TODO: special method for this, after host is obtained from the form within a text field
        new Thread("Connect") {
            public void run () {
                try {
                    client.connect(5000, host, NetworkRegister.port);
                    // Server communication after connection can go here, or in Listener#connected().
                } catch (IOException ex) {
                    ex.printStackTrace();
                    System.exit(1);
                }
            }
        }.start();
    }

    private void setAndFillBattleScene(EnterRoomWithSetup opponentInRoom, String chosenGlobalSetupId) {


        Gdx.app.postRunnable(new Runnable() {
            @Override
            public void run() {
                CombatSetup playerCS;
                CombatSetup opponentCS;

                switch(chosenGlobalSetupId) {
                    case "1":
                        playerCS = createCombatGroupExample1();
                    default:
                        playerCS = createCombatGroupExample2();
                }

                switch(opponentInRoom.globalSetupId) {
                    case "1":
                        opponentCS =createCombatGroupExample2();
                    default:
                        opponentCS = createCombatGroupExample1();
                }
                BattleStageGroup bsg = new BattleStageGroup("fairy-forest.jpg", playerCS, opponentCS);
                game.setScreen(new InBattleScreen(game, bsg));
//                game.setBattleScreen();
            }
        });
//        game.setBattleScreen();
    }

}
