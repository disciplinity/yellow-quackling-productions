package game.screens.preferences;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import game.MyGdxGame;

import static game.constants.MenuComponentsSize.*;
import static game.constants.TexturePaths.PLATFORM;
import static game.constants.TexturePaths.START_TREE_BG;

public class PreferenceScreen implements Screen {
    private MyGdxGame game;
    private SpriteBatch spriteBatch;
    private Stage preferenceStage;
    private Skin skin;

    private Texture background;
    private Texture platformTexture;
    private ImageButton startFight;

    private InputMultiplexer inputMultiplexer;

    public PreferenceScreen(CharacterSetup characterSetup) {
        this.game = MyGdxGame.getInstance();
        this.spriteBatch = this.game.getSpriteBatch();
        this.preferenceStage = new Stage(new ScreenViewport(), spriteBatch);
        this.skin = new Skin(Gdx.files.internal("data/uiskin.json"));

        background = new Texture(START_TREE_BG);
        platformTexture = new Texture(PLATFORM);

        inputMultiplexer = new InputMultiplexer(preferenceStage);
        createPlatforms();
        // fill preferences stage by characters
        preferenceStage.addActor(characterSetup);
        startGame();

    }

    private void startGame() {
        startFight = new ImageButton(skin, "startFight");
        startFight.setSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        startFight.getStyle().imageUp = new TextureRegionDrawable(new TextureRegion(new Texture(Gdx.files.internal("ui/Into the Fight.jpg"))));
        startFight.setPosition(BUTTON_X, CENTER_BUTTON_Y_CORD);
        startFight.addListener(new InputListener() {
            @Override
            public void touchUp(InputEvent event, float x, float y, int pointer, int button ) {
                // switch to the battle screen
            }

            @Override
            public boolean touchDown(InputEvent event, float x, float y, int pointer, int button ) {
                return true;
            }
        });
        preferenceStage.addActor(startFight);
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(inputMultiplexer);
    }

    @Override
    public void render(float delta) {
        spriteBatch.begin();
        spriteBatch.draw(background, 0, 0);
        spriteBatch.end();

        preferenceStage.act();
        preferenceStage.draw();

    }

    @Override
    public void resize(int width, int height) {
        preferenceStage.getViewport().update(width, height, true);
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
        preferenceStage.dispose();
    }

    private void createPlatforms() {
        for (int i = 0; i < 3; i++) {
            Platform platform = new Platform();
            platform.setPosition(-200, -70 + i * 220);
            preferenceStage.addActor(platform);
        }
    }

    class Platform extends Actor {
        @Override
        public void draw(Batch spriteBatch, float parentAlpha) {
            spriteBatch.draw(platformTexture, getX(), getY());
        }
    }
}
