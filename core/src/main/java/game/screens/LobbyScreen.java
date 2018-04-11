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

public class LobbyScreen implements Screen {
    private MyGdxGame game;
    private Stage stage;
    private Skin skin;

    private Table table;
    private TextButton joinBattleButton;

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

        joinBattleButton = new TextButton("Join Battle!", skin);


        joinBattleButton.addListener(new ClickListener(){
            @Override
            public void clicked(InputEvent event, float x, float y) {
                GameClient client = MyGdxGame.getInstance().getClient();
                client.joinBattle();
            }
        });

        table.defaults().uniform().space(10).width(100);
        table.row();
        table.add(joinBattleButton).colspan(2);
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