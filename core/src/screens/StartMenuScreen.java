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
import network.kryonet.ui_frame.ConnectionTestScreen;


public class StartMenuScreen implements Screen {

    private static final int BUTTON_WIDTH = 280;
    private static final int BUTTON_HEIGHT = 70;
    private static final int BUTTON_X = 510;

    private static final int SIGN_UP_BUTTON_Y = 531;
    private static final int REGISTER_Y = 431;
    private static final int EXIT_BUTTON_Y = 331;

    private MyGdxGame game;
    private SpriteBatch batch;
    private Stage menuStage;
    private Texture background, startButton, startButtonActive, registerButton, exitButton;
    private ButtonCreator startButtonObj, startButtonActiveObj, registerButtonObj, exitButtonObj;

    private boolean hover;

    /**
     * Main menu
     * @param game Game platform
     */
    public StartMenuScreen(MyGdxGame game) {
        this.game = game;
        this.batch = this.game.getSpriteBatch();
        this.menuStage = new Stage(new ScreenViewport());

        // load the images
        background = new Texture(Gdx.files.internal("menu/background.jpg"));

        startButton = new Texture(Gdx.files.internal("menu/start.jpg"));
        startButtonActive = new Texture(Gdx.files.internal("menu/start_active.jpg"));
        registerButton = new Texture(Gdx.files.internal("menu/register.jpg"));
        exitButton = new Texture(Gdx.files.internal("menu/exit.jpg"));


        // create buttons
        // TODO: check their proper scalability and resolution adaptation and make button in action
        startButtonObj = new ButtonCreator(startButton, BUTTON_X, SIGN_UP_BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
        startButtonActiveObj = new ButtonCreator(startButton, BUTTON_X, SIGN_UP_BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
        registerButtonObj = new ButtonCreator(registerButton, BUTTON_X, REGISTER_Y, BUTTON_WIDTH, BUTTON_HEIGHT);
        exitButtonObj = new ButtonCreator(exitButton, BUTTON_X, EXIT_BUTTON_Y, BUTTON_WIDTH, BUTTON_HEIGHT);

        startButtonObj.addListener(new InputListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                System.out.println("touch - true");
                hover = true;
                //menuStage.addActor(startButtonActiveObj);
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor toActor) {
                System.out.println("dont touch - false");
                hover = false;
            }

            @Override
            public boolean touchDown(InputEvent inputEvent, float x, float y, int pointer, int button) {
                game.setScreen(new ConnectionTestScreen(game));
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

        registerButtonObj.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent inputEvent, float x, float y, int pointer, int button) {
                game.setScreen(new RegistrationScreen(game));
                dispose();
                return true;
            }
        });

        menuStage.addActor(startButtonObj);
        menuStage.addActor(registerButtonObj);
        menuStage.addActor(exitButtonObj);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(menuStage);
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
        menuStage.getViewport().update(width, height, true);
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
        registerButton.dispose();
        exitButton.dispose();
        menuStage.dispose();
    }
}