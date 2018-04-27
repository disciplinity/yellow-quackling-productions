package game.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.Touchable;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import game.MyGdxGame;
import network.client.GameClient;

import java.io.IOException;

public class ConnectionTestScreen implements Screen{
    private Stage stage;
    private Skin skin;

    private Table table;
    private Label nameLabel;
    private Label passLabel;
    private TextField nameField;
    private TextField passField;
    private TextButton connectButton;
    private Label alertField;


    public ConnectionTestScreen() {
        drawTestConnectionMenu();

        Gdx.input.setInputProcessor(stage);
    }

    /**
     * Initialize elements to draw on the screen.
     */
    private void initMenuElements() {
        skin = new Skin(Gdx.files.internal("data/uiskin.json"));
        stage = new Stage(new ScreenViewport());
        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        alertField = new Label("", skin);
        nameField = new TextField("Jaro", skin);
        nameLabel = new Label("Username:", skin);
        passField = new TextField("secret", skin);
        passLabel = new Label("Password:", skin);
        connectButton = new TextButton("Connect", skin);

        /*
         * Connect to a server with entered credentials to a server.
         */
        connectButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
//                if (!connectButton.isDisabled()) {
                    try {
                        setAlert("Connecting...");
//                        connectButton.setDisabled(true);
                        connectButton.setTouchable(Touchable.disabled);
                        // TODO: connect here?
                        GameClient client = new GameClient();
                        MyGdxGame.getInstance().setClient(client);
                        client.sendCredentials(nameField.getText(), passField.getText());
                    } catch (IOException e) {
                        setAlert("No connection to the server");
//                        connectButton.setDisabled(false);
                        connectButton.setTouchable(Touchable.enabled);
                    }
//                }
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
    public void setAlert(String message) {
        alertField.setText(message);
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
