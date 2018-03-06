package network.kryonet.client_chat;

import battle.BattleStageGroup;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import game.MyGdxGame;
import models.CombatGroup;
import network.kryonet.register.NetworkRegister;
import screens.InBattleScreen;

import java.io.IOException;

public class GameClient {
    Client client;
    String name;

    public GameClient(CombatGroup chosenByPlayerCombatGroup, MyGdxGame game, String host) {
        client = new Client();
        client.start();

        // Register the class to be sent over the network
        NetworkRegister.register(client);


        client.addListener(new Listener() {
            public void connected (Connection connection) {
                NetworkRegister.RegisterPlayerCombatGroup registerCombatGroup = new NetworkRegister.RegisterPlayerCombatGroup();
                registerCombatGroup.combatGroup = chosenByPlayerCombatGroup;
                client.sendTCP(registerCombatGroup);
            }

            public void received (Connection connection, Object object) {
                if (object instanceof NetworkRegister.UpdateNames) {
                    NetworkRegister.UpdateNames updateNames = (NetworkRegister.UpdateNames)object;
                    // Depends on scene. Modify data on current scene, which is not here represented... so..
                    // TODO: screen(or even Game object or something that client could interact with the screen when it is switcher)
                    // should be passed to client object, so it's methods could be called and content modified
                    return;
                }

                if (object instanceof NetworkRegister.RegisterPlayerCombatGroup) {
                    NetworkRegister.RegisterPlayerCombatGroup opponentGroup = (NetworkRegister.RegisterPlayerCombatGroup)object;
                    // TODO setAndFillBattleScene(opponentGroup);
                    CombatGroup opponentCombatGroup = opponentGroup.combatGroup; // not used now
                    BattleStageGroup bsg = new BattleStageGroup("fairy-forest.jpg", chosenByPlayerCombatGroup, opponentCombatGroup);
                    game.setScreen(new InBattleScreen(game, bsg));
                    return;
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

}
