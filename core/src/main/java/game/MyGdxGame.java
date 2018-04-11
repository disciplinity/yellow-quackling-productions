package game;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.*;
import game.actors.GameCharacter;
import game.models.combat.BattleStageGroup;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import lombok.Getter;
import game.models.combat.CombatSetup;
import game.screens.CombatScreen;

import static game.actors.CharacterFactory.createCombatGroupMock1;
import static game.actors.CharacterFactory.createCombatGroupMock2;


// TODO: NOW IT'S JUST A MODEL FOR SPRITE BATCH :D
public class MyGdxGame extends Game {


    @Getter
    private SpriteBatch spriteBatch;
    private Music music;
    private Cursor customCursor;


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

//        CombatSetup playerCS = createCombatGroupExample1();
//        CombatSetup opponentCS = createCombatGroupExample2();
//        BattleStageGroup bsg = new BattleStageGroup("fairy-forest.jpg", playerCS, opponentCS);
//        this.setScreen(new CombatScreen(this, bsg));

        ////////// ::::DANGER ZONE:::::
        CombatSetup cs = createCombatGroupMock1();
        CombatSetup cso = createCombatGroupMock2();
        BattleStageGroup battleStageGroup = new BattleStageGroup("fairy-forest.jpg", cs, cso);
        ////////// ::::DANGER ZONE:::::

        this.setScreen(new CombatScreen(this, battleStageGroup));
//        this.setScreen(new StartMenuScreen(this));
    }


    public void resize(int width, int height) {
    }

    public void setBattleScreen() {

//        CombatSetup playerCS = createCombatGroupExample1();
//        CombatSetup opponentCS = createCombatGroupExample2();
//        BattleStageGroup bsg = new BattleStageGroup("fairy-forest.jpg", playerCS, opponentCS);
//        this.setScreen(new CombatScreen(this, bsg));
    }


    public void render() {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        screen.render(0.033f);
    }


    public void dispose() {
        screen.dispose();
        spriteBatch.dispose();
    }


}