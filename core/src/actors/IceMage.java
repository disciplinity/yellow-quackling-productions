package actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import components.GraphicsComponent;
import components.StatComponent;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;


public class IceMage extends Actor {

    private TextureRegion[][] textureRegion;

    private Animation<TextureRegion> standingAnimation;
    private float stateTime;
    private int x, y, width, height;
    private boolean flip;
    private GraphicsComponent graphicsComponent;

    @Getter
    private StatComponent statComponent;
    public static IceMage currentlyChosen = null;
    private IceMage reference;


    public IceMage(StatComponent statComponent, String spritesheetName, int emptySpaces, int COLUMN_NUMBER, int ROW_NUMBER, int x, int y, int width, int height, boolean flip) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.flip = flip;
        this.statComponent = statComponent;

        graphicsComponent = new GraphicsComponent(spritesheetName, COLUMN_NUMBER, ROW_NUMBER);
        textureRegion = graphicsComponent.getTextureRegions();

        TextureRegion[] animationSheet = new TextureRegion[COLUMN_NUMBER * ROW_NUMBER - emptySpaces];
        for (int i = 0, counter = 0; i < ROW_NUMBER; i++) {
            for (int j = 0; j < COLUMN_NUMBER; j++) {
                // Stop adding sprites for animation if encountered empty cell when empty cell
                if (i == ROW_NUMBER - 1 && j == COLUMN_NUMBER - emptySpaces) {
                    break;
                }
                animationSheet[counter++] = textureRegion[i][j];
            }
        }
        standingAnimation = new Animation<>(0.120f, animationSheet);
        standingAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);
        stateTime = 0;

        this.setBounds(x, y, width, height);


        this.addListener(new InputListener() {
            public boolean touchDown (InputEvent event, float x, float y, int pointer, int button) {
                currentlyChosen = reference;
                return true;
            }

            public void touchUp (InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("up");
            }
        });
        reference = this;
   }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        stateTime += Gdx.graphics.getDeltaTime();
        TextureRegion currentFrame = standingAnimation.getKeyFrame(stateTime, false);
        batch.draw(currentFrame, flip ? x+width : x, y, flip ? -width : width, height);
    }

}
