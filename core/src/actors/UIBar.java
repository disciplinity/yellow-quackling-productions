package actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Actor;
import components.StatComponent;

public class UIBar extends Actor {

    private ShapeRenderer shapeRenderer = new ShapeRenderer();
    private Texture leonardo = new Texture("leonardo.jpeg");
    private Texture cardTexture = new Texture("card.jpg");
    private Texture elementTexture = new Texture("elements.png");
    private TextureRegion[][] elements = TextureRegion.split(elementTexture,
            elementTexture.getWidth() / 3,
            elementTexture.getHeight() / 2);

    private StatComponent currentChosenPlayerStats;


    @Override
    public void draw(Batch batch, float parentAlpha) {

        batch.end();
        shapeRenderer.setColor(Color.DARK_GRAY);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        // Gray background
        shapeRenderer.rect(0, 0, 1280, 230);

        // Golden rectangles
//        shapeRenderer.setColor(Color.GOLD);
//        shapeRenderer.rect(50, 20, 135, 190);
//        shapeRenderer.rect(200, 20, 135, 190);
//        shapeRenderer.rect(350, 20, 135, 190);


//        shapeRenderer.setColor(Color.ROYAL);
//        shapeRenderer.rectLine(new Vector2(50, 20), new Vector2(50, 210), 10);
//        shapeRenderer.rectLine(new Vector2(185, 210), new Vector2(185, 20), 10);
//        shapeRenderer.rectLine(new Vector2(50, 20), new Vector2(50, 210), 10);
//        shapeRenderer.rectLine(new Vector2(50, 20), new Vector2(185, 20), 10);
        shapeRenderer.end();
        batch.begin();
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Raleway-Medium.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 16;
        BitmapFont font12 = generator.generateFont(parameter);

        // Cards and Leonardo

        batch.draw(leonardo, 50, 20, 200, 190);

        batch.draw(elements[1][0], 275, 170, 30, 30);
        batch.draw(elements[0][0], 275, 135, 30, 30);
        batch.draw(elements[1][2], 275, 100, 30, 30);
        batch.draw(elements[0][2], 275, 65, 30, 30);
        batch.draw(elements[0][1], 275, 30, 30, 30);

        batch.draw(cardTexture, 380, 20, 135, 190);
        batch.draw(cardTexture, 530, 20, 135, 190);
        batch.draw(cardTexture, 680, 20, 135, 190);

        if (IceMage.currentlyChosen != null) {
            font12.draw(batch, String.valueOf(IceMage.currentlyChosen.getStatComponent().getIntellect()), 312,  193);
            font12.draw(batch, String.valueOf(IceMage.currentlyChosen.getStatComponent().getSpirit()), 312,  158);
            font12.draw(batch, String.valueOf(IceMage.currentlyChosen.getStatComponent().getStrength()), 312,  123);
            font12.draw(batch, String.valueOf(IceMage.currentlyChosen.getStatComponent().getAgility()), 312,  88);
            font12.draw(batch, String.valueOf(IceMage.currentlyChosen.getStatComponent().getLight()), 312, 53);
        }
    }
}
