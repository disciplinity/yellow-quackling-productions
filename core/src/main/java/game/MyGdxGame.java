package game;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.*;
import game.actors.GameCharacter;
import game.actors.GameCharacterType;
import game.components.SpellBookComponent;
import game.models.combat.BattleStageGroup;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import game.screens.preferences.CharacterSetup;
import game.screens.preferences.PreferenceScreen;
import game.screens.StartMenuScreen;
import game.screens.LobbyScreen;
import game.screens.StartMenuScreen;
import game.session.CombatSession;
import game.session.GameSession;
import game.spells.animations.SpellAnimation;
import lombok.Getter;
import lombok.Setter;
import network.client.GameClient;
import network.manager.NetworkManager;
import ui.combat.GearGroup;
import ui.combat.SpellGroup;


public class MyGdxGame extends Game {

    @Getter
    private SpriteBatch spriteBatch;
    private Music music;
    private Cursor customCursor;

    //TODO: Does client should be here?
    @Getter
    @Setter
    private GameClient client;

    //TODO: Create a new Game class to separate graphic and game logic
    @Getter
    private CombatSession combatSession = new CombatSession(false);


    /**
     * Singleton pattern for our Game object
     */
    public static class MyGdxGameHolder {
        static final MyGdxGame HOLDER_INSTANCE = new MyGdxGame();
    }

    public static MyGdxGame getInstance() {
        return MyGdxGameHolder.HOLDER_INSTANCE;
    }

    public static Thread spellRenderThread;


    public void create() {
        spriteBatch = new SpriteBatch();
        music = Gdx.audio.newMusic(Gdx.files.internal("music/background_music.mp3"));
        music.setLooping(true);
        music.play();
        Pixmap pixmap = new Pixmap(Gdx.files.internal("cursor.png"));

        customCursor = Gdx.graphics.newCursor(pixmap, 0, 5);
        Gdx.graphics.setCursor(customCursor);

        this.setScreen(new StartMenuScreen(this));
        //setPreferenceScreen();
    }

    public void createCombatSession(boolean myTurn) {
        combatSession = new CombatSession(myTurn);
    }

//    /**
//     * Called when the Application is resized.
//     */
//    public void resize(int width, int height) {
//    }

    /**
     * Screen are not disposed automatically, so if we don't want to keep the, dispose the last one.
     */
    public void setLobbyScreen() {
        this.screen.dispose();
        //CharacterSetup characterSetup = new CharacterSetup();
        //this.setScreen(new PreferenceScreen(characterSetup));
        this.setScreen(new LobbyScreen());
    }

    public void setBattleScreen() {

//        BattleStageGroup battleStageGroup = new BattleStageGroup("fairy-forest.jpg", cs, cso);
//        this.setScreen(new CombatScreen(this, bsg));
    }

    public void setPreferenceScreen() {
        CharacterSetup characterSetup = new CharacterSetup();
        this.setScreen(new PreferenceScreen(characterSetup));
    }


    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
//        screen.render(0.033f);
        screen.render(Gdx.graphics.getDeltaTime());
    }


    public void dispose() {
        screen.dispose();
        spriteBatch.dispose();
    }
//
//    public void startNewRenderThread(GameCharacter whoClicked, GameCharacter target, NetworkManager.DealDamageResponse dealDamageResponse) {
//        Thread th = new Thread(() -> {
//            if (whoClicked.getGraphicsComponent().isOpponent()) {
//                SpellAnimation spellAnimation = whoClicked.getSpellBookComponent().getSpellSet().getAllSpells().get(0).getSpellAnimation();
//                if (spellAnimation == null) return;
//                spellAnimation.setSpellStartX(whoClicked.getX() + whoClicked.getWidth());
//                spellAnimation.setSpellStartY(whoClicked.getY() + (whoClicked.getHeight() / 2));
//                spellAnimation.setSpellVelocityX(spellAnimation.getSpellStartX());
//                spellAnimation.setSpellVelocityY(spellAnimation.getSpellStartY());
//                spellAnimation.setSpellSound(Gdx.audio.newSound(Gdx.files.internal("sounds/fireball_sound.wav")));
//                spellAnimation.getPe().load(Gdx.files.internal("particles/fireball_particle"), Gdx.files.internal(""));
//                spellAnimation.setSpellEndX(target.getX() + target.getWidth());
//                spellAnimation.setSpellEndY(target.getY() + target.getHeight() / 4);
//                spellAnimation.setCaster(whoClicked);
//                whoClicked.setCastingSpell(true);
//            }
//
//            target.setHealth(target.getHealth() - dealDamageResponse.getDealtDamage());
//            SpellBookComponent.currentSpellChosen = null;
//            GearGroup.fillItemSlots();
//            SpellGroup.fillSpellSlots();
//        });
//        th.start();
//    }
}