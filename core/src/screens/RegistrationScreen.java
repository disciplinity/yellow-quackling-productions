package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.assets.loaders.AssetLoader;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;
import game.MyGdxGame;
import menu.ButtonCreator;

import java.awt.*;

public class RegistrationScreen implements Screen {
    private static final int BUTTON_WIDTH = 280;
    private static final int BUTTON_HEIGHT = 70;

    private MyGdxGame game;
    private SpriteBatch batch;
    private Stage menuStage;
    private Texture background, logInButton;
    private ButtonCreator logInButtonObj;

    public RegistrationScreen(MyGdxGame gdxGame, SpriteBatch batch) {
        this.game = gdxGame;
        this.batch = batch;

        // load images
        background = new Texture(Gdx.files.internal("menu/backgruond.jpg"));
        logInButton = new Texture(Gdx.files.internal("menu/log_in.jpg"));

        //create buttons
        logInButtonObj = new ButtonCreator(logInButton, 0, 0, BUTTON_WIDTH, BUTTON_HEIGHT);




        menuStage.addActor(logInButtonObj);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(background, 0, 0, 1280, 800);
        batch.end();

        menuStage.draw();
        menuStage.act();
    }

    @Override
    public void resize(int width, int height) {

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
        background.dispose();
        menuStage.dispose();
    }
}
