package com.mygdx.game;


import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import lombok.Getter;
import screens.InBattleScreen;
import screens.StartMenuScreen;

public class MyGdxGame extends Game {
    public static final int WIDTH = 1280;
    public static final int HEIGHT = 800;

    //A Sprite holds the geometry and colour data of a texture, this means the positional data ( such as it’s X and Y location )
    //are stored in the Sprite.
    @Getter
    public SpriteBatch batch;
    private InBattleScreen inBattleScreen;
    private StartMenuScreen menuPanel;

    @Override
    public void create () {
        batch = new SpriteBatch();
        //inBattleScreen = new InBattleScreen(this, batch);
        menuPanel = new StartMenuScreen(this, batch);
        setScreen(menuPanel);
    }

    public void resize (int width, int height) {
        // See below for what true means.
    }

    public void render () {
        Gdx.gl.glClearColor(100, 150, 190, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        getScreen().render(0.033f);
    }

    public void dispose() {

    }

}