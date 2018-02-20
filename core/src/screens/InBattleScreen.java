package screens;


import actors.IceMage;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.actions.Actions;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import lombok.Getter;
import lombok.Setter;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

public class InBattleScreen implements Screen {

    @Getter @Setter
    private Stage stage;
    private SpriteBatch spriteBatch;
    private Game game;
    private Texture background;

    public InBattleScreen(Game game, SpriteBatch spriteBatch) {
        this.game = game;
        this.spriteBatch = spriteBatch;
        background = new Texture("background.jpeg");
        spriteBatch.begin();
        spriteBatch.draw(background, 0, 0);
        spriteBatch.end();


        stage = new Stage(new ScreenViewport(), spriteBatch);
        Gdx.input.setInputProcessor(stage);

        IceMage iceMage1 = new IceMage(0,0, 150, 150, false);
        IceMage iceMage2 = new IceMage(0,160, 150, 150, false);
        IceMage iceMage3 = new IceMage(0,320, 150, 150, false);

        IceMage iceMage4 = new IceMage((int)stage.getWidth() - 150,0, 150, 150, true);
        IceMage iceMage5 = new IceMage((int)stage.getWidth() - 150,160, 150, 150, true);
        IceMage iceMage6 = new IceMage((int)stage.getWidth() - 150,320, 150, 150, true);

        stage.addActor(iceMage1);
        stage.addActor(iceMage2);
        stage.addActor(iceMage3);
        stage.addActor(iceMage4);
        stage.addActor(iceMage5);
        stage.addActor(iceMage6);


    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
//        stage.getBatch().draw(, 0, 0, WORLD_WIDTH, WORLD_HEIGHT);

        stage.act();
        stage.draw();

    }

    public void resize (int width, int height) {
        // See below for what true means.
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}


