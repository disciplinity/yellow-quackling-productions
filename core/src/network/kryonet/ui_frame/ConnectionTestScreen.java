package network.kryonet.ui_frame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import game.MyGdxGame;
import models.CombatGroup;
import network.kryonet.client_chat.GameClient;

import static factory.TestFactories.createCombatGroupExample1;
import static factory.TestFactories.createCombatGroupExample2;

public class ConnectionTestScreen implements Screen{
    private MyGdxGame game;
    private Stage stage;
    private Skin skin;

    private Table table;
    private Label nameLabel;
    private Label addressLabel;
    private TextField nameField;
    private TextField addressField;
    private TextButton connectButton;

    private TextButton heroPackOneButton;
    private TextButton heroPackTwoButton;

    private CombatGroup chosenCombatGroup;

    public ConnectionTestScreen(MyGdxGame game) {
        this.game = game;

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
        nameLabel = new Label("Name:", skin);
        addressField = new TextField("", skin);
        addressLabel = new Label("Address:", skin);
        connectButton = new TextButton("Connect", skin);


        heroPackOneButton = new TextButton("Hero pack one", skin);
        heroPackTwoButton = new TextButton("Hero pack two", skin);
        ButtonGroup buttonGroup = new ButtonGroup(heroPackOneButton, heroPackTwoButton);
        buttonGroup.setMaxCheckCount(1);
        buttonGroup.setMinCheckCount(0);
        buttonGroup.setUncheckLast(true);

        connectButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                Gdx.app.log("Clicked", "Yes, you did!");
                // TODO: create client. Create CombatGroup for him. Wait for opponent.
                // TODO: We should pass some 'Game' object to the client with all needed info... (chosen heroes etc..)
                chosenCombatGroup = heroPackOneButton.isChecked() ? createCombatGroupExample1() : createCombatGroupExample2();
                new GameClient(chosenCombatGroup, game, addressField.getMessageText());
                System.out.println(nameField.getText());
            }
        });

        table.defaults().uniform().space(10).width(100);
        table.add(nameLabel);
        table.add(nameField);
        table.row();
        table.add(addressLabel);
        table.add(addressField);
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
