package game;

import game.models.combat.BattleStageGroup;
import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import game.screens.ConnectionTestScreen;
import game.screens.LobbyScreen;
import lombok.Getter;
import game.models.combat.CombatSetup;
import game.screens.CombatScreen;
import lombok.Setter;
import network.client.GameClient;

import static game.actors.CharacterFactory.createCombatGroupMock1;
import static game.actors.CharacterFactory.createCombatGroupMock2;


public class MyGdxGame extends Game {

    @Getter
    private SpriteBatch spriteBatch;
    //TODO: Does client should be here?
    @Getter
    @Setter
    private GameClient client;

    public static class MyGdxGameHolder {
        static final MyGdxGame HOLDER_INSTANCE = new MyGdxGame();
    }

    public static MyGdxGame getInstance() {
        return MyGdxGameHolder.HOLDER_INSTANCE;
    }


    public void create() {
        spriteBatch = new SpriteBatch();

        ////////// ::::DANGER ZONE:::::
        CombatSetup cs = createCombatGroupMock1();
        CombatSetup cso = createCombatGroupMock2();
        BattleStageGroup battleStageGroup = new BattleStageGroup("fairy-forest.jpg", cs, cso);
        ////////// ::::DANGER ZONE:::::

//        this.setScreen(new CombatScreen(this, battleStageGroup));
        this.setScreen(new ConnectionTestScreen());
//        this.setScreen(new StartMenuScreen(this));
    }


    public void resize(int width, int height) {
    }


    public void setLobbyScreen() {
        this.setScreen(new LobbyScreen());
    }

    public void setBattleScreen() {

//        BattleStageGroup battleStageGroup = new BattleStageGroup("fairy-forest.jpg", cs, cso);
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