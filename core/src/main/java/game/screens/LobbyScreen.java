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

public class LobbyScreen implements Screen {
    private MyGdxGame game;
    private Stage stage;
    private Skin skin;

    private Table table;
    private TextButton joinBattleButton;
    private Label alertField;

    public LobbyScreen() {
        this.game = MyGdxGame.getInstance();

        skin = new Skin(Gdx.files.internal("data/uiskin.json"));
        stage = new Stage(new ScreenViewport());

        drawLobby();

        // InputMultiplexer im = new InputMultiplexer(stage); excessive
        Gdx.input.setInputProcessor(stage); // in case multiplexer is needed it is passed as an arg here
    }

    private void drawLobby() {
        table = new Table();
        table.setFillParent(true);
        stage.addActor(table);

        alertField = new Label("", skin);
        joinBattleButton = new TextButton("Join Battle!", skin);


        joinBattleButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                joinBattleButton.getStyle().disabled = skin.getDrawable("default-round-down");
//                joinBattleButton.getStyle().disabled = joinBattleButton.getStyle().down; Or this way
                joinBattleButton.setDisabled(true);
                alertField.setText("Waiting for the opponent...");
                joinBattleButton.setTouchable(Touchable.disabled);
                MyGdxGame.getInstance().getClient().joinBattle();
            }
        });

        table.defaults().uniform().space(10).width(100);
        table.add(alertField);
        table.row();
        table.add(joinBattleButton).colspan(2);
    }



    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
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