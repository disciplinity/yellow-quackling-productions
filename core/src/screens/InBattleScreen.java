package screens;


import ui.UIBar;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import components.StatComponent;
import lombok.Getter;
import lombok.Setter;
import screens.screen.utilities.Background;

public class InBattleScreen implements Screen {

    @Getter @Setter
    private Stage stage;
    private SpriteBatch spriteBatch;
    private Game game;
    private Background background;
//    private CharacterSpawner characterSpawner;

    public InBattleScreen(Game game, SpriteBatch spriteBatch) {
        this.game = game;
        this.spriteBatch = spriteBatch;

        stage = new Stage(new ScreenViewport(), spriteBatch);
        Gdx.input.setInputProcessor(stage);
        StatComponent statComponent = new StatComponent(3, 5, 12, 3, 2);
        StatComponent statComponent1 = new StatComponent(10, 2, 3, 1, 50);
        StatComponent statComponent2 = new StatComponent(1000, 1000, 1000,1000, 1000);

        background = new Background("fairy-forest.jpg");


//        IceMage iceMage1 = new IceMage(statComponent,  40,140 + 175, 120, 120);
//        IceMage iceMage2 = new IceMage(statComponent1, 200,180 + 175, 155, 120);
//        IceMage iceMage3 = new IceMage(statComponent2, 70,270 + 175);

//        IceMage iceMage4 = new IceMage(statComponent, (int)stage.getWidth() - 120 - 40,140 + 175, 120, 120, true);
//        IceMage iceMage5 = new IceMage(statComponent1,  (int)stage.getWidth() - 155 - 200,180 + 175, 155, 120, true);
//        IceMage iceMage6 = new IceMage(statComponent2,  (int)stage.getWidth() - 220 - 70,270 + 175, 220, 200, true);

        stage.addActor(new UIBar());
        stage.addActor(background);
    }


    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        stage.act();
        stage.draw();

    }

    public void resize (int width, int height) {
        // See below for what true means.
        stage.getViewport().update(width, height, true);
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


