package game;


import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import javafx.stage.Stage;
import lombok.Getter;
import screens.InBattleScreen;
import screens.StartMenuScreen;

public class MyGdxGame extends Game {

    @Getter
    private SpriteBatch spriteBatch;
    private InBattleScreen inBattleScreen;
    private StartMenuScreen menuPanel;

    public void create () {
        spriteBatch = new SpriteBatch();
        inBattleScreen = new InBattleScreen(spriteBatch);
//        this.setScreen(inBattleScreen);
        menuPanel = new StartMenuScreen(this, spriteBatch);
        this.setScreen(menuPanel);
    }

    public void resize (int width, int height) {
        // See below for what true means.
    }

    public void render () {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        screen.render(0.033f);
    }

    public void dispose() {
        inBattleScreen.dispose();
        menuPanel.dispose();
    }


}