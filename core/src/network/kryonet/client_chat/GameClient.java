package network.kryonet.client_chat;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import network.kryonet.register.NetworkRegister;

import java.awt.*;
import java.io.IOException;

public class GameClient {
    ChatFrame chatFrame;
    Client client;
    String name;

    public GameClient() {
        client = new Client();
        client.start();

        // Register the class to be sent over the network
        NetworkRegister.register(client);


        client.addListener(new Listener() {
            public void connected (Connection connection) {
                NetworkRegister.RegisterName registerName = new NetworkRegister.RegisterName();
                registerName.name = name;
                client.sendTCP(registerName);
            }

            public void received (Connection connection, Object object) {
                if (object instanceof NetworkRegister.UpdateNames) {
                    NetworkRegister.UpdateNames updateNames = (NetworkRegister.UpdateNames)object;
                    // Depends on scene. Modify data on current scene, which is not here represented... so..
                    // TODO: screen(or even Game object or something that client could interact with the screen when it is switcher)
                    // should be passed to client object, so it's methods could be called and content modified
                    chatFrame.setNames(updateNames.names);
                    return;
                }

                if (object instanceof NetworkRegister.OpponentFound) {
                    NetworkRegister.OpponentFound opponentGroup = (NetworkRegister.OpponentFound)object;
                    // TODO setAndFillBattleScene(opponentGroup);
                    // OR(better): Switch screen
                    // https://stackoverflow.com/questions/25837013/switching-between-screens-libgdx
                    return;
                }
            }

            public void disconnected (Connection connection) {
                EventQueue.invokeLater(() -> {
                    // TODO: Return to lobby. Could be implemented only when client will know about the screen.
                    chatFrame.dispose();
                });
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
