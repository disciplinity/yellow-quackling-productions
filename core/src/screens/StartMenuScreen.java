package screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.MyGdxGame;
import module.Object;


public class StartMenuScreen implements Screen {

    private static final int BUTTON_WIDTH = 280;
    private static final int BUTTON_HEIGHT = 70;

    private Game game;
    private Stage menuStage;
    private Texture background, logInButton, exitButton, boom;
    private SpriteBatch batch;
    private Object logInButtonObj, exitButtonObj, boomsObj;

    public StartMenuScreen(Game game, SpriteBatch spriteBatch) {
        this.game = game;
        this.menuStage = new Stage(new ScreenViewport(), spriteBatch);
        this.batch = spriteBatch;

        background = new Texture(Gdx.files.internal("backgrond.jpg"));
        logInButton = new Texture(Gdx.files.internal("log_in.jpg"));
        exitButton = new Texture(Gdx.files.internal("exit.jpg"));

        logInButtonObj = new Object(logInButton, 510, 381, BUTTON_WIDTH, BUTTON_HEIGHT);
        exitButtonObj = new Object(exitButton, 510, 281, BUTTON_WIDTH, BUTTON_HEIGHT);

        logInButtonObj.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent inputEvent, float x, float y, int pointer, int button) {
                game.setScreen(new InBattleScreen(batch));
                return false;
            }
        });

        menuStage.addActor(logInButtonObj);    }


    @Override
    public void show() {
        //мы в игре, переключаемся на экран

        Gdx.input.setInputProcessor(menuStage);

    }

    @Override
    public void render(float delta) {
        //вызывается постоянно, цикл, дельта показывает, сколько времени прошло с первого кадра до следующего

        batch.begin();

        batch.draw(background, 0, 0);
        batch.end();
        //boomsObj.draw(batch);

        menuStage.draw();
        menuStage.act();

//        logInButtonObj.draw(batch);
//        if (Gdx.input.isTouched()) {
//            this.dispose();
//            game.setScreen(new InBattleScreen(game, batch));
//        }

//        exitButtonObj.draw(batch);
//        if (Gdx.input.isTouched()) {
//            Gdx.app.exit();
//        }


    }

    @Override
    public void resize(int width, int height) {
        //изменение размера экрана, пересоздавать камеры
        // See below for what true means.
        //stage.getViewport().update(width, height, true);
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
        batch.dispose();

    }
}