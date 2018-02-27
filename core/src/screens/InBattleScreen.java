package screens;


import battle.UIBarGroup;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import lombok.Getter;
import lombok.Setter;
import battle.BattleStageGroup;

public class InBattleScreen implements Screen {

    @Getter @Setter
    private Stage battleStage;

    @Getter @Setter
    private Stage uiStage;

    private SpriteBatch spriteBatch;

    private Texture currentBackground;


    public InBattleScreen(SpriteBatch spriteBatch) {

        this.spriteBatch = spriteBatch;

        battleStage = new Stage(new ScreenViewport(), spriteBatch);
        uiStage = new Stage(new ScreenViewport(), spriteBatch);

        battleStage.addActor(new BattleStageGroup("fairy-forest.jpg"));
        uiStage.addActor(new UIBarGroup());

        currentBackground = new Texture("fairy-forest.jpg");
    }



    @Override
    public void show() {
        Gdx.input.setInputProcessor(battleStage);
    }

    @Override
    public void render(float delta) {

        spriteBatch.begin();
        spriteBatch.draw(currentBackground, 0, 230, 1280, 570);
        spriteBatch.end();

        battleStage.act();
        battleStage.draw();

        uiStage.draw();


    }

    public void resize (int width, int height) {
        battleStage.getViewport().update(width, height, true);
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
        battleStage.dispose();
        uiStage.dispose();
    }

}


