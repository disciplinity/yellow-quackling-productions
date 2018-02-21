package components;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import lombok.Data;

@Data public class GraphicsComponent {

    private Texture texture;
    private TextureRegion[][] textureRegions;


    public GraphicsComponent(String textureName, int COLUMN_NUMBER, int ROW_NUMBER) {
        texture = new Texture(textureName);
        textureRegions = TextureRegion.split(texture,
                texture.getWidth() / COLUMN_NUMBER,
                        texture.getHeight() / ROW_NUMBER);
    }

    public Texture getTexture() {
        return texture;
    }

    public TextureRegion[][] getTextureRegions() {
        return textureRegions;
    }
}
