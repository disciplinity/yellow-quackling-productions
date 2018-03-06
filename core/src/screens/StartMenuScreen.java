package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import game.MyGdxGame;
import menu.ButtonCreator;


public class StartMenuScreen implements Screen {

    private static final int BUTTON_WIDTH = 280;
    private static final int BUTTON_HEIGHT = 70;

    private static final int LOGIN_BUTTON_X = 510;
    private static final int LOGIN_BUTTON_Y = 381;
    private static final int EXIT_BUTTON_X = 510;
    private static final int EXIT_BUTTON_Y = 281;

    private MyGdxGame game;
    private SpriteBatch batch;
    private Stage menuStage;
    private Texture background, logInButton, logInButtonActive, exitButton;
    private ButtonCreator logInButtonObj, logInButtonActiveObj, exitButtonObj;

    private boolean hover;


    public StartMenuScreen(MyGdxGame game, SpriteBatch batch) {
        this.game = game;
        this.batch = batch;
        this.menuStage = new Stage(new ScreenViewport());

        // load the images
        background = new Texture(Gdx.files.internal("menu/background.jpg"));
        logInButton = new Texture(Gdx.files.internal("menu/log_in.jpg"));
        logInButtonActive = new Texture(Gdx.files.internal("menu/log_in_active.jpg"));
        exitButton = new Texture(Gdx.files.internal("menu/exit.jpg"));

        // create buttons
        // TODO: check their proper scalability and resolution adaptation
        logInButtonObj = new ButtonCreator(logInButton, LOGIN_BUTTON_X, LOGIN_BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
        exitButtonObj = new ButtonCreator(exitButton, LOGIN_BUTTON_X, LOGIN_BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
        logInButtonActiveObj = new ButtonCreator(logInButtonActive, LOGIN_BUTTON_X, LOGIN_BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);

        logInButtonObj.addListener(new InputListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                System.out.println("touch - true");
                hover = true;
                menuStage.addActor(logInButtonActiveObj);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                System.out.println("dont touch - false");
                hover = false;
                menuStage.addActor(logInButtonObj);
            }

            @Override
            public boolean touchDown(InputEvent inputEvent, float x, float y, int pointer, int button) {
                game.setScreen(new InBattleScreen(game, batch));
                dispose();
                return true;
            }
        });

        exitButtonObj.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent inputEvent, float x, float y, int pointer, int button) {
                Gdx.app.exit();
                return false;
            }
        });

        menuStage.addActor(logInButtonObj);
        menuStage.addActor(exitButtonObj);
    }

    public Texture changeButtonsStyle() {
        if (!hover) {
            return logInButton;
        } else {
            return logInButtonActive;
        }
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