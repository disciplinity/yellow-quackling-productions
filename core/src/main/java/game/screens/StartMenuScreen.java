package game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import game.MyGdxGame;
import ui.menu.ImageCreator;

public class StartMenuScreen implements Screen {

    private static final int BUTTON_WIDTH = 280;
    private static final int BUTTON_HEIGHT = 70;
    private static final int BUTTON_X = 510;

    private static final int SIGN_IN_Y = 531;
    private static final int SIGN_UP_Y = 431;
    private static final int EXIT_BUTTON_Y = 331;

    private MyGdxGame game;
    private SpriteBatch batch;
    private Stage menuStage;
    private Skin skin;

    private ImageButton loginIn, signUp, exit;

    private Texture background, woodBoard;
    private Table table;
    private Label nameLabel, passLabel, alertField;
    private ImageCreator woodBoardActor;
    TextField nameField, passField;
    TextButton connectButton;

    /**
     * Main game.screens.menu
     * @param game Game platform
     */
    public StartMenuScreen(MyGdxGame game) {
        this.game = game;
        this.batch = this.game.getSpriteBatch();
        this.menuStage = new Stage(new ScreenViewport());
        this.skin = new Skin(Gdx.files.internal("data/uiskin.json"));

        createLoginButton();
        createSignupButton();
        createExitButton();

        loadImages();
    }

    private void loadImages() {
        background = new Texture(Gdx.files.internal("ui/background.jpg"));
        woodBoard = new Texture(Gdx.files.internal("ui/wood-board.png"));

        woodBoardActor = new ImageCreator(woodBoard, 490 , 251 , 300, 300);
    }

    private void createLoginButton() {
        loginIn = new ImageButton(skin, "logIn");
        loginIn.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        loginIn.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("ui/login.jpg"))));
        loginIn.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("ui/log_in_active.jpg"))));
        loginIn.setPosition(BUTTON_X, SIGN_IN_Y);
        loginIn.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button ) {
                menuStage.addActor(woodBoardActor);
                drawTestConnectionMenu();
                loginIn.remove();
                signUp.remove();
                exit.remove();
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button ) {
                return true;
            }
        });
        menuStage.addActor(loginIn);
    }

    private void createSignupButton() {
        signUp = new ImageButton(skin, "signUp");
        signUp.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        signUp.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("ui/signup.jpg"))));
        signUp.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("ui/start.jpg"))));
        signUp.setPosition(BUTTON_X, SIGN_UP_Y);
        signUp.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button ) {
                //menuStage.addActor(woodBoardActor);
                //drawTestConnectionMenu();
                //loginIn.remove();
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button ) {
                return true;
            }
        });
        menuStage.addActor(signUp);
    }

    private void createExitButton() {
        exit = new ImageButton(skin, "exit");
        exit.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        exit.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("ui/exit.jpg"))));
        exit.getStyle().imageDown = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("ui/exit_active.jpg"))));
        exit.setPosition(BUTTON_X, EXIT_BUTTON_Y);
        exit.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button ) {
                Gdx.app.exit();
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button ) {
                return true;
            }
        });
        menuStage.addActor(exit);
    }

    /**
     * Initialize elements to draw on the screen.
     */
    private void initMenuElements() {
        table = new Table();
        table.setFillParent(true);
        menuStage.addActor(table);

        alertField = new Label("", skin);
        nameField = new TextField("jaroslav", skin);
        nameLabel = new Label("Username:", skin);
        passField = new TextField("123", skin);
        passLabel = new Label("Password:", skin);
        connectButton = new TextButton("Connect", skin);

        // connect to a server with entered credentials to a server
        connectButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                ScreenController.authorize();
            }
        });
    }

    /**
     * Draw initialized items.
     */
    private void drawTestConnectionMenu() {
        initMenuElements();

        table.defaults().uniform().space(10).width(100);
        table.add(alertField);
        table.row();
        table.add(nameLabel);
        table.add(nameField);
        table.row();
        table.add(passLabel);
        table.add(passField);
        table.row();
        table.add(connectButton).colspan(2);
    }

    /**
     * Set alert message according to the request (bad login/password)
     * @param message alert message
     */
    void setAlert(String message) {
        alertField.setText(message);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(menuStage);
    }

    @Override
    public void render(float delta) {
        //stage.act(Gdx.graphics.getDeltaTime()); is it needed?
        batch.begin();
        batch.draw(background, 0, 0, 1280, 800);
        batch.end();

        menuStage.act();
        menuStage.draw();

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

    }
}