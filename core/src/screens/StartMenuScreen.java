package screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import game.MyGdxGame;
import module.Object;


public class StartMenuScreen implements Screen {

    private static final int BUTTON_WIDTH = 280;
    private static final int BUTTON_HEIGHT = 70;

    private MyGdxGame game;
    private SpriteBatch batch;
    private Stage menuStage;
    private Texture background, logInButton, exitButton;
    private Object logInButtonObj, exitButtonObj;


    public StartMenuScreen(MyGdxGame game) {
        this.game = game;
        this.batch = this.game.getSpriteBatch();
        this.menuStage = new Stage(new ScreenViewport());

        // load the images
        background = new Texture(Gdx.files.internal("backgrond.jpg"));
        logInButton = new Texture(Gdx.files.internal("log_in.jpg"));
        exitButton = new Texture(Gdx.files.internal("exit.jpg"));

        // create buttons
        // TODO: make coordinates for buttons constants too And check their proper scalability and resolution adaptation
        logInButtonObj = new Object(logInButton, 510, 381, BUTTON_WIDTH, BUTTON_HEIGHT);
        exitButtonObj = new Object(exitButton, 510, 281, BUTTON_WIDTH, BUTTON_HEIGHT);

        logInButtonObj.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent inputEvent, float x, float y, int pointer, int button) {
                game.setScreen(new InBattleScreen(game));
                dispose();
                return true;
            }
        });

        menuStage.addActor(logInButtonObj);
    }


    @Override
    public void show() {
        Gdx.input.setInputProcessor(menuStage);
    }


    @Override
    public void render(float delta) {
        //вызывается постоянно, цикл, дельта показывает, сколько времени прошло с первого кадра до следующего

        batch.begin();

        batch.draw(background, 0, 0, 1280, 800);
        batch.end();

        menuStage.draw();
        menuStage.act();
    }


    @Override
    public void resize(int width, int height) {
        //изменение размера экрана, пересоздавать камеры
        menuStage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {
        //сварачиваем игру
    }

    @Override
    public void resume() {
        //разварачиваем игру
    }

    @Override
    public void hide() {
        //переключение на другой экран в игре (меню - сама игра)
    }

    @Override
    public void dispose() {
        //уничтожение/освобождение всех рессурсов, закрываем программу
        background.dispose();
        logInButton.dispose();
        exitButton.dispose();
        menuStage.dispose();
    }
}