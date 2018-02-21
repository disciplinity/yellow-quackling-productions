package screens;


import actors.IceMage;
import actors.UIBar;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import lombok.Getter;
import lombok.Setter;

public class InBattleScreen implements Screen {

    @Getter @Setter
    private Stage stage;
    private SpriteBatch spriteBatch;
    private Game game;
    private Texture background;
    private ShapeRenderer shapeRenderer;

    public InBattleScreen(Game game, SpriteBatch spriteBatch) {
        this.game = game;
        this.spriteBatch = spriteBatch;
        background = new Texture("background.jpg");

        stage = new Stage(new ScreenViewport(), spriteBatch);
        Gdx.input.setInputProcessor(stage);

        IceMage iceMage1 = new IceMage(40,140 + 175, 120, 120, false);
        IceMage iceMage2 = new IceMage(200,180 + 175, 120, 120, false);
        IceMage iceMage3 = new IceMage(70,270 + 175, 120, 120, false);

        IceMage iceMage4 = new IceMage((int)stage.getWidth() - 120 - 40,140 + 175, 120, 120, true);
        IceMage iceMage5 = new IceMage((int)stage.getWidth() - 120 - 200,180 + 175, 120, 120, true);
        IceMage iceMage6 = new IceMage((int)stage.getWidth() - 120 - 70,270 + 175, 120, 120, true);

        stage.addActor(new UIBar());
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
        spriteBatch.begin();
        spriteBatch.draw(background, 0, 175, 1280, 800);
        spriteBatch.end();

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


