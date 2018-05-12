package game.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import lombok.Data;

@Data
public class FontGenerator {

    public FreeTypeFontGenerator generator;
    public FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    public BitmapFont font12;

    public FontGenerator(String fontPath) {
        this.generator = new FreeTypeFontGenerator(Gdx.files.internal(fontPath));
        this.parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
    }
}
