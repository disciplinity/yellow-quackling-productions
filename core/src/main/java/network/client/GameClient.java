package network.client;

import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;
import game.MyGdxGame;
import game.screens.ScreenController;
import game.session.GameSession;
import game.spells.SpellType;
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

                    startSession();
                    return;
                }

                if (object instanceof GetCombatSetupResponse) {
                    GetCombatSetupResponse response = (GetCombatSetupResponse) object;
                    Log.debug("[DEBUG] Got player combat info.");
                    startLobby(response.getPlayerCombatInfo());
                }

                // TODO:
                if (object instanceof BeginBattleResponse) {
                    BeginBattleResponse response = (BeginBattleResponse) object;
                    Log.debug("[DEBUG] Got Begin Battle Response.");
                    Log.debug("Halo there" + response.getPlayerCombatInfo().toString());
                    Log.debug("Halo there" + response.getOpponentCombatInfo().toString());
                    beginBattle(response.getPlayerCombatInfo(), response.getOpponentCombatInfo());
                }

                if (object instanceof DealDamageResponse) {
                    DealDamageResponse response = (DealDamageResponse) object;
                    MyGdxGame.getInstance().getCombatSession()
                            .getHeroHealthAtSlot()[response.getTargetSlotId()] -= response.getDealtDamage();
                    //TODO: Render Fireball and new health value [Jaro]
                }

                if (object instanceof PlayerTurnResponse) {
                    PlayerTurnResponse response = (PlayerTurnResponse) object;
                    MyGdxGame.getInstance().getCombatSession().setMyTurn(response.isYourTurn());
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


    // TODO: Use this, Jaro! :)
    /**
     *
     * @param spellType Type from enum
     * @param casterSlotId casterSlot [value from 0 to 2]
     * @param targetSlotId targetSlot [value from 3 to 5]
     */
    private void sendDamageRequest(SpellType spellType, int casterSlotId, int targetSlotId) {
        DealDamageRequest request = new DealDamageRequest();
        request.setCastedSpellType(spellType);
        request.setDealerSlotId(casterSlotId);
        request.setTargetSlotId(targetSlotId);
        request.setUserToken(userToken);
        client.sendTCP(request);
    }

    // TODO: Jaro, use this one too for the button! :)
    private void endTurn() {
        TurnEndRequest request = new TurnEndRequest();
        client.sendTCP(request);
    }

    private void startSession() {
        Log.debug("Requesting combat setup");
        GetCombatSetupRequest request = new GetCombatSetupRequest();
        request.setUserToken(userToken);
        client.sendTCP(request);
    }

    private void startLobby(PlayerCombatInfo playerInfo) {
        GameSession gs = GameSession.getInstance();
        gs.setPlayerCombatSetup(playerInfo);
        ScreenController.setLobbyScreen();
    }

    private void beginBattle(PlayerCombatInfo player, PlayerCombatInfo opponent) {
        ScreenController.setBattleScreen(player, opponent);
    }

    private void createPreferencesWithCharacters(PlayerCombatInfo player, PlayerCombatInfo opponent) {
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

}
