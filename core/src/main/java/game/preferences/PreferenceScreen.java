package game.preferences;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import game.MyGdxGame;

import static game.constants.TexturePaths.PLATFORM;
import static game.constants.TexturePaths.START_TREE_BG;

public class PreferenceScreen implements Screen {
    private MyGdxGame game;
    private SpriteBatch spriteBatch;
    private Stage stage;

    private Texture background;
    private Texture platformTexture;

    public PreferenceScreen(CharacterSetup characterSetup) {
        this.game = MyGdxGame.getInstance();
        this.spriteBatch = this.game.getSpriteBatch();
        this.stage = new Stage(new ScreenViewport(), spriteBatch);

        background = new Texture(START_TREE_BG);
        platformTexture = new Texture(PLATFORM);
        createPlatforms();

        stage.addActor(characterSetup);

    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        spriteBatch.begin();
        spriteBatch.draw(background, 0, 0);
        spriteBatch.end();

        stage.draw();
        stage.act();
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

    }

    private void createPlatforms() {
        for (int i = 0; i < 3; i++) {
            Platform platform = new Platform();
            platform.setPosition(-200, -70 + i * 220);
            stage.addActor(platform);
        }
    }

    class Platform extends Actor {
        @Override
        public void draw(Batch spriteBatch, float parentAlpha) {
            spriteBatch.draw(platformTexture, getX(), getY());
        }
    }
}
