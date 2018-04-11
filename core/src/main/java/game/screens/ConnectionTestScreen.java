package game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import game.MyGdxGame;
import network.client.GameClient;

public class ConnectionTestScreen implements Screen{
    private MyGdxGame game;
    private Stage stage;
    private Skin skin;

    private Table table;
    private Label nameLabel;
    private Label passLabel;
    private TextField nameField;
    private TextField passField;
    private TextButton connectButton;

//    private TextButton heroPackOneButton;
//    private TextButton heroPackTwoButton;

//    private String chosenCombatSetupId;

    public ConnectionTestScreen() {
        this.game = MyGdxGame.getInstance();

        skin = new Skin(Gdx.files.internal("data/uiskin.json"));
        stage = new Stage(new ScreenViewport());

        drawTestConnectionMenu();

        // InputMultiplexer im = new InputMultiplexer(stage); excessive
        Gdx.input.setInputProcessor(stage); // in case multiplexer is needed it is passed as an arg here
    }

    private void drawTestConnectionMenu() {
        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        nameField = new TextField("", skin);
        nameLabel = new Label("Username:", skin);
        passField = new TextField("localhost", skin);
        passLabel = new Label("Password:", skin);
        connectButton = new TextButton("Connect", skin);


        /* LEGACY */
//        heroPackOneButton = new TextButton("Hero pack one", skin);
//        heroPackTwoButton = new TextButton("Hero pack two", skin);
//        ButtonGroup<TextButton> buttonGroup = new ButtonGroup<>(heroPackOneButton, heroPackTwoButton);
//        buttonGroup.setMaxCheckCount(1);
//        buttonGroup.setMinCheckCount(0);
//        buttonGroup.setUncheckLast(true);

        connectButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameClient client = new GameClient();
                MyGdxGame.getInstance().setClient(client);
                client.sendCredentials(nameField.getMessageText() , passField.getMessageText());
            }
        });

        table.defaults().uniform().space(10).width(100);
        table.add(nameLabel);
        table.add(nameField);
        table.row();
        table.add(passLabel);
        table.add(passField);
//        table.row();
//        table.add(heroPackOneButton).colspan(2);
//        table.row();
//        table.add(heroPackTwoButton).colspan(2);
        table.row();
        table.add(connectButton).colspan(2);
    }




    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
//        stage.act(Gdx.graphics.getDeltaTime()); is it needed?
        stage.draw();
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
        stage.dispose();
    }
}
