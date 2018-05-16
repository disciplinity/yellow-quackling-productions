package network.client;

import com.badlogic.gdx.Gdx;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;
import com.esotericsoftware.minlog.Log;
import game.MyGdxGame;
import game.actors.GameCharacter;
import game.components.SpellBookComponent;
import game.models.combat.BattleStageGroup;
import game.screens.ScreenController;
import game.session.CombatSession;
import game.session.GameSession;
import game.spells.SpellType;
import game.spells.animations.SpellAnimation;
import network.manager.NetworkManager.*;
import network.manager.NetworkManager;
import network.manager.PlayerCombatInfo;
import ui.combat.GearGroup;
import ui.combat.SpellGroup;

import java.io.IOException;
import java.util.Arrays;

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
                    BattleStageGroup.characterSlots[response.getTargetSlotId()].getActor().setHealth(BattleStageGroup.characterSlots[response.getTargetSlotId()].getActor().getHealth() - response.getDealtDamage());
                    //                    //TODO: Render Fireball and new health value [Jaro]

//                    if (SpellBookComponent.currentSpellChosen != null) {
//                        SpellBookComponent.currentSpellChosen.setClicked(false);
//                    }

                    GameCharacter whoClicked = BattleStageGroup.characterSlots[response.getDealerSlotId()].getActor();
                    GameCharacter reference = BattleStageGroup.characterSlots[response.getTargetSlotId()].getActor();

//                    if (whoClicked.getGraphicsComponent().isOpponent()) {
//                        if (whoClicked.getSlotId() == 0) {
//                            whoClicked.setSlotId(3);
//                        }
//                        else if (whoClicked.getSlotId() == 1) {
//                            whoClicked.setSlotId(4);
//                        }
//                        else if (whoClicked.getSlotId() == 2) {
//                            whoClicked.setSlotId(5);
//                        }
//
//                        reference.setSlotId(reference.getSlotId() - 3);
//
//                    }

                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            Gdx.app.postRunnable(new Runnable() {
                                @Override
                                public void run() {
//                                    if (!whoClicked.getGraphicsComponent().isOpponent())
//                                    if (SpellBookComponent.currentSpellChosen == null) return;
                                    SpellAnimation spellAnimation = whoClicked.getSpellBookComponent().getSpellSet().getAllSpells().get(0).getSpellAnimation();
                                    if (whoClicked.getGraphicsComponent().isOpponent()) {
                                        spellAnimation.setEnemyAnimation(true);
                                    }
                                    else {
                                        spellAnimation.setEnemyAnimation(false);
                                    }
                                    spellAnimation.setSpellStartX(whoClicked.getX() + whoClicked.getWidth() );
                                    spellAnimation.setSpellStartY(whoClicked.getY() + (whoClicked.getHeight() / 2));
                                    spellAnimation.setSpellVelocityX(spellAnimation.getSpellStartX());
                                    spellAnimation.setSpellVelocityY(spellAnimation.getSpellStartY());
                                    spellAnimation.setSpellEndX(reference.getX() + reference.getWidth());
                                    spellAnimation.setSpellEndY(reference.getY() + reference.getHeight() / 4);
//                                    if (spellAnimation == null) return;

//                                    spellAnimation.setSpellStartX(whoClicked.getX() + whoClicked.getWidth() );
//                                    spellAnimation.setSpellStartY(whoClicked.getY() + (whoClicked.getHeight() / 2));
//                                    spellAnimation.setSpellVelocityX(spellAnimation.getSpellStartX());
//                                    spellAnimation.setSpellVelocityY(spellAnimation.getSpellStartY());
//                                    spellAnimation.setSpellEndX(reference.getX() + reference.getWidth());
//                                    spellAnimation.setSpellEndY(reference.getY() + reference.getHeight() / 4);
                                    spellAnimation.setSpellSound(Gdx.audio.newSound(Gdx.files.internal("sounds/fireball_sound.wav")));
                                    spellAnimation.getPe().load(Gdx.files.internal("particles/fireball_particle"), Gdx.files.internal(""));

                                    spellAnimation.setCaster(whoClicked);
                                    whoClicked.setCastingSpell(true);
                                    reference.setHealth(reference.getHealth() - response.getDealtDamage());

                                    SpellBookComponent.currentSpellChosen = null;

                                    if (GameCharacter.currentlyChosen != null && !GameCharacter.currentlyChosen.getGraphicsComponent().isOpponent()) {
                                        GearGroup.fillItemSlots();
                                        SpellGroup.fillSpellSlots();
                                    }

                                }
                            });
                        }
                    }).start();

                    if (whoClicked.getHealth() < 0 || reference.getHealth() < 0) {
                        System.out.println("DEAD");
                    }

//                    MyGdxGame.getInstance().startNewRenderThread(whoClicked, reference, response);





//                        if (whoClicked.getGraphicsComponent().isOpponent()) {
//                            SpellAnimation spellAnimation = whoClicked.getSpellBookComponent().getSpellSet().getAllSpells().get(0).getSpellAnimation();
//                            if (spellAnimation == null) return;
//                            spellAnimation.setSpellStartX(whoClicked.getX() + whoClicked.getWidth());
//                            spellAnimation.setSpellStartY(whoClicked.getY() + (whoClicked.getHeight() / 2));
//                            spellAnimation.setSpellVelocityX(spellAnimation.getSpellStartX());
//                            spellAnimation.setSpellVelocityY(spellAnimation.getSpellStartY());
//                            spellAnimation.setSpellSound(Gdx.audio.newSound(Gdx.files.internal("sounds/fireball_sound.wav")));
//                            spellAnimation.getPe().load(Gdx.files.internal("particles/fireball_particle"), Gdx.files.internal(""));
//                            spellAnimation.setSpellEndX(reference.getX() + reference.getWidth());
//                            spellAnimation.setSpellEndY(reference.getY() + reference.getHeight() / 4);
//                            spellAnimation.setCaster(whoClicked);
//                            whoClicked.setCastingSpell(true);
//                        }
//
//                    reference.setHealth(reference.getHealth() - response.getDealtDamage());
//                    SpellBookComponent.currentSpellChosen = null;
//                    GearGroup.fillItemSlots();
//                    SpellGroup.fillSpellSlots();
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
    public void sendDamageRequest(SpellType spellType, int casterSlotId, int targetSlotId) {
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
        CombatSession combatSession = MyGdxGame.getInstance().getCombatSession();

        PlayerCombatInfo playerCombatInfo = player;
        for (int i = 0, j = 0; i < 5; i++, j++) {
            if (i > 2) playerCombatInfo = opponent;
            if (j > 2) j = 0;

            combatSession.getHeroHealthAtSlot()[i] = playerCombatInfo.getHeroes().get(j).getHealth();
        }

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
