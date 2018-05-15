package game;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.*;
import game.models.combat.BattleStageGroup;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import game.preferences.CharacterSetup;
import game.preferences.PreferenceScreen;
import game.screens.ConnectionTestScreen;
import game.screens.LobbyScreen;
import lombok.Getter;
import game.models.combat.CombatSetup;
import lombok.Setter;
import network.client.GameClient;


public class MyGdxGame extends Game {

    @Getter
    private SpriteBatch spriteBatch;
    private Music music;
    private Cursor customCursor;

    //TODO: Does client should be here?
    @Getter
    @Setter
    private GameClient client;


    /**
     * Singleton pattern for our Game object
     */
    public static class MyGdxGameHolder {
        static final MyGdxGame HOLDER_INSTANCE = new MyGdxGame();
    }

    public static MyGdxGame getInstance() {
        return MyGdxGameHolder.HOLDER_INSTANCE;
    }


    public void create() {
        spriteBatch = new SpriteBatch();
        music = Gdx.audio.newMusic(Gdx.files.internal("music/background_music.mp3"));
        music.setLooping(true);
        music.play();
        Pixmap pixmap = new Pixmap(Gdx.files.internal("cursor.png"));

        customCursor = Gdx.graphics.newCursor(pixmap, 0, 5);
        Gdx.graphics.setCursor(customCursor);
//        Pixmap pm = new Pixmap(Gdx.files.internal("cursor.png"));
//        Gdx.graphics.setCursor(Gdx.graphics.newCursor(pm, 30, 255));
//        pm.dispose();

        ////////// ::::DANGER ZONE:::::
//        CombatSetup cs = createCombatGroupMock1();
//        CombatSetup cso = createCombatGroupMock2();
//        BattleStageGroup battleStageGroup = new BattleStageGroup("fairy-forest.jpg", cs, cso);
//        this.setScreen(new CombatScreen(this, battleStageGroup));
        ////////// ::::DANGER ZONE:::::

        this.setScreen(new ConnectionTestScreen());
        CharacterSetup characterSetup = new CharacterSetup(cs, cso);
        this.setScreen(new PreferenceScreen(characterSetup));

//        this.setScreen(new StartMenuScreen(this));
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
        this.setScreen(new LobbyScreen());
    }

    public void setBattleScreen() {

//        BattleStageGroup battleStageGroup = new BattleStageGroup("fairy-forest.jpg", cs, cso);
//        this.setScreen(new CombatScreen(this, bsg));
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


}