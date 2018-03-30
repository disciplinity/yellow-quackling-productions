package main.java.game.screens;


import main.java.ui.combat.UIBarGroup;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import main.java.game.MyGdxGame;
import lombok.Getter;
import lombok.Setter;
import main.java.game.models.combat.BattleStageGroup;

import static main.java.game.constants.TexturePaths.FAIRY_FOREST_TEXTURE;

public class CombatScreen implements Screen {

    @Getter @Setter
    private Stage battleStage;
    @Getter @Setter
    private Stage uiStage;

    private MyGdxGame game;
    private SpriteBatch spriteBatch;
    private Texture currentBackground;
    private InputMultiplexer inputMultiplexer;

    // TODO: May be pass something else but BattleStageGroup?
    public CombatScreen(MyGdxGame game, BattleStageGroup battleStageGroup) {
        this.game = game;
        this.spriteBatch = this.game.getSpriteBatch();

        // create stages
        battleStage = new Stage(new ScreenViewport(), spriteBatch);
        uiStage = new Stage(new ScreenViewport(), spriteBatch);
        // create and fill input processor
        inputMultiplexer = new InputMultiplexer(battleStage, uiStage);

        // fill stages
        // TODO: create game.factory method for filling background for the stage and call this method here
        // OR
        // TODO: make texture file string as a constant
        // OR
        // TODO: make method to change background in a BattleStageGroup with a method. Make file-string constant there.
        battleStage.addActor(battleStageGroup);
        uiStage.addActor(new UIBarGroup());

        // TODO: Make getter from BattleStageGroup? And call this getter not here, but at spriteBatch.draw(...) below.
        currentBackground = new Texture(FAIRY_FOREST_TEXTURE);

    }


    @Override
    public void show() {
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void render(float delta) {

        spriteBatch.begin();
        // TODO: Here.
        spriteBatch.draw(currentBackground, 0, 230, 1280, 570);
        spriteBatch.end();

        battleStage.draw();
        battleStage.act();


        uiStage.draw();
        uiStage.act();
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


