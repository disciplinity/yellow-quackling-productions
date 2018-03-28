package game.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import game.actors.GameCharacter;
import lombok.Data;
import lombok.Getter;

@Data
public class GraphicsComponent {
    private final int COLUMN_AMOUNT;
    private final int ROW_AMOUNT;

    private Animation<TextureRegion> standingAnimation;
    private TextureRegion[][] textureRegions;
    private TextureRegion currentFrame;
    private Texture texture;
    private float stateTime = 0;
    private boolean isOpponent = false;

    @Getter
    private int sizeWidth;
    @Getter
    private int sizeHeight;

    // TODO: MOVE IT TO THE CONTROLLER(OR MAYBE VIEW?) OF THE COMBAT SCENE
    public static GameCharacter currentlyChosen = null;

    public GraphicsComponent(String textureName, int COLUMN_AMOUNT, int ROW_AMOUNT, int sizeWidth, int sizeHeight, int endColumn) {
        this.texture = new Texture(textureName);
        this.COLUMN_AMOUNT = COLUMN_AMOUNT;
        this.ROW_AMOUNT = ROW_AMOUNT;
        this.sizeHeight = sizeHeight;
        this.sizeWidth = sizeWidth;

        textureRegions = TextureRegion.split(texture,
                texture.getWidth() / COLUMN_AMOUNT,
                texture.getHeight() / ROW_AMOUNT);
        // TODO: MOAR CONSTANTS OR FLEXIBILITY
        standingAnimation = new Animation<>(0.120f, this.getAnimationSheet(1, 1, 1, endColumn));
        standingAnimation.setPlayMode(Animation.PlayMode.LOOP_PINGPONG);

    }

    public InputListener getTouchListener(GameCharacter reference) {
        return new InputListener() {

            public void touchUp(InputEvent event, float x, float y, int pointer, int button) {
                // TODO: Some set method for this (in controller/view of combat screen of course)
                currentlyChosen = reference;
            }
        };
    }

    public void setOpponent() {
        isOpponent = true;
    }

    public void draw(Batch batch, float parentAlpha, float x, float y, float width, float height) {
        batch.end();
        stateTime += Gdx.graphics.getDeltaTime();
        currentFrame = standingAnimation.getKeyFrame(stateTime, true);

        if (!currentFrame.isFlipX()) {
            currentFrame.flip(isOpponent, false);
        }
        batch.begin();
        batch.draw(currentFrame, x, y, width, height);
    }

    public Texture getTexture() {
        return texture;
    }

    public TextureRegion[][] getTextureRegions() {
        return textureRegions;
    }

    public TextureRegion getSpecificTextureRegion(int row, int col) {
        return textureRegions[row][col];
    }


    /**
     * @param startRow Row from which you want to start taking your sprites on a sprite sheet.
     * @param startCol Column from which you want to start taking your sprites...
     * @param endRow   At which row stop counting.
     * @param endCol   At which column stop counting.
     * @return 1D array (animation sheet).
     */
    private TextureRegion[] getAnimationSheet(int startRow, int startCol, int endRow, int endCol) {


        int amountOfSprites = (endRow - startRow) * ROW_AMOUNT + (endCol - startCol) + 1;
        TextureRegion[] animationSheet = new TextureRegion[amountOfSprites];

        for (int row = startRow - 1, c = 0; row < endRow; row++) {
            for (int col = startCol - 1; col < COLUMN_AMOUNT; col++) {
                animationSheet[c++] = textureRegions[row][col];

            }
        }
        return animationSheet;
    }

}
