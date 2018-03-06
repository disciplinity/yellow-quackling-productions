package game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import lombok.Getter;
import screens.StartMenuScreen;

public class MyGdxGame extends Game {

    @Getter
    private SpriteBatch spriteBatch;

    public void create () {
        spriteBatch = new SpriteBatch();
        this.setScreen(new StartMenuScreen(this, spriteBatch));
    }


    public void resize (int width, int height) {
    }


    public void render () {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        screen.render(0.033f);
    }


    public void dispose() {
        screen.dispose();
        spriteBatch.dispose();
    }


}