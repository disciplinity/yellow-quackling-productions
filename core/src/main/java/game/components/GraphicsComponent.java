package game.components;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import game.actors.GameCharacter;
import lombok.Data;
import lombok.Getter;
import ui.combat.GearGroup;
import ui.combat.SpellGroup;

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

    public ClickListener getTouchListener(GameCharacter reference) {
        return new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                // TODO: Some set method for this (in controller/view of combat screen of course)
                GameCharacter whoClicked = GameCharacter.currentlyChosen;
                GameCharacter.currentlyChosen = reference;
                if (SpellBookComponent.currentSpellChosen != null) {
                    SpellBookComponent.currentSpellChosen.setClicked(false);
                    if (reference.getGraphicsComponent().isOpponent) {

                        whoClicked.setSpellStartX(whoClicked.getX() + whoClicked.getWidth());
                        whoClicked.setSpellVelocityX(whoClicked.getSpellStartX());
                        whoClicked.setSpellEndX(reference.getX() + reference.getWidth());
                        whoClicked.setSpellStartY(whoClicked.getY() + whoClicked.getHeight() / 4);
                        whoClicked.setSpellVelocityY(whoClicked.getSpellStartY());
                        whoClicked.setSpellEndY(reference.getY() + reference.getHeight() / 4);
                        whoClicked.setCastingSpell(true);
                        GameCharacter.currentlyChosen = whoClicked;
                    }
                }

                SpellBookComponent.currentSpellChosen = null;
                GearGroup.fillItemSlots();
                SpellGroup.fillSpellSlots();
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

    public void setPosition(GameCharacter c, int x, int y) {
        c.setPosition(x, y);
        System.out.println(c + " x: " + x + "; y: " + y);
    }

    public void setBounds(GameCharacter c) {
        c.setBounds(c.getX(), c.getY(), c.getWidth(), c.getHeight());
        System.out.println(c.getStatComponent().getAgility());
    }

}
