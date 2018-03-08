package game.components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import lombok.Data;

@Data public class GraphicsComponent {

    private Texture texture;
    private TextureRegion[][] textureRegions;

    private final int COLUMN_AMOUNT;
    private final int ROW_AMOUNT;


    public GraphicsComponent(String textureName, int COLUMN_AMOUNT, int ROW_AMOUNT) {
        texture = new Texture(textureName);
        this.COLUMN_AMOUNT = COLUMN_AMOUNT;
        this.ROW_AMOUNT = ROW_AMOUNT;

        textureRegions = TextureRegion.split(texture,
                texture.getWidth() / COLUMN_AMOUNT,
                        texture.getHeight() / ROW_AMOUNT);


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
     *
     * @param startRow Row from which you want to start taking your sprites on a sprite sheet.
     * @param startCol Column from which you want to start taking your sprites...
     * @param endRow At which row stop counting.
     * @param endCol At which column stop counting.
     * @return 1D array (animation sheet).
     */
    public TextureRegion[] getAnimationSheet(int startRow, int startCol, int endRow, int endCol) {


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
