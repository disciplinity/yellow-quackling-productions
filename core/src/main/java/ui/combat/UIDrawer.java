package main.java.ui.combat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import main.java.game.actors.GameCharacter;

public class UIDrawer {

    private FreeTypeFontGenerator generator;
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    private BitmapFont font12;

    public UIDrawer() {
        generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Raleway-Medium.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 16;
        font12 = generator.generateFont(parameter);
    }

    public void drawElements(Batch batch, TextureRegion[][] el) {
        batch.draw(el[1][0], 275, 170, 30, 30);
        batch.draw(el[0][0], 275, 135, 30, 30);
        batch.draw(el[1][2], 275, 100, 30, 30);
        batch.draw(el[0][2], 275, 65, 30, 30);
        batch.draw(el[0][1], 275, 30, 30, 30);
    }

    public void drawCards(Batch batch, Texture c) {
        batch.draw(c, 380, 20, 135, 190);
        batch.draw(c, 530, 20, 135, 190);
        batch.draw(c, 680, 20, 135, 190);
    }

    public void drawGrayBackground(ShapeRenderer shapeRenderer) {
        shapeRenderer.setColor(Color.DARK_GRAY);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        // Gray background
        shapeRenderer.rect(0, 0, 1280, 230);
        shapeRenderer.end();
    }

    public void drawStats(Batch batch) {
        font12.draw(batch, String.valueOf(GameCharacter.currentlyChosen.getStatComponent().getIntellect()), 312, 193);
//        font12.draw(batch, String.valueOf(GameCharacter.currentlyChosen.getStatComponent().getSpirit()), 312,  158);
        font12.draw(batch, String.valueOf(GameCharacter.currentlyChosen.getStatComponent().getStrength()), 312, 123);
        font12.draw(batch, String.valueOf(GameCharacter.currentlyChosen.getStatComponent().getAgility()), 312, 88);
//        font12.draw(batch, String.valueOf(GameCharacter.currentlyChosen.getStatComponent().getLight()), 312, 53);
    }
}
