package ui.combat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Group;
import game.actors.GameCharacter;
import game.components.GraphicsComponent;

public class UIBarGroup extends Group {

    private GraphicsComponent graphicsComponent;
    private ShapeRenderer shapeRenderer = new ShapeRenderer();
    private Texture leonardo = new Texture("leonardo.jpeg");
    private Texture cardTexture = new Texture("card.jpg");
    private TextureRegion[][] elements;

    public UIBarGroup() {
        // TODO: Graphic component with EXCESS parameters(eg for animation purposes) -> Change something or create new reduced constructor
        graphicsComponent = new GraphicsComponent("elements.png", 3, 2, 100, 100, 5);
        elements = graphicsComponent.getTextureRegions();
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.end();

        drawGrayBackground();

        batch.begin();

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Raleway-Medium.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 16;
        BitmapFont font12 = generator.generateFont(parameter);

        // Drawing all of the UI elements. TODO:: Implement leonardo as 'worn-items-object', as well as add spells etc

        batch.draw(leonardo, 50, 20, 200, 190);

        batch.draw(elements[1][0], 275, 170, 30, 30);
        batch.draw(elements[0][0], 275, 135, 30, 30);
        batch.draw(elements[1][2], 275, 100, 30, 30);
        batch.draw(elements[0][2], 275, 65, 30, 30);
        batch.draw(elements[0][1], 275, 30, 30, 30);

        batch.draw(cardTexture, 380, 20, 135, 190);
        batch.draw(cardTexture, 530, 20, 135, 190);
        batch.draw(cardTexture, 680, 20, 135, 190);

        if (GameCharacter.currentlyChosen != null) {
            font12.draw(batch, String.valueOf(GameCharacter.currentlyChosen.getStatComponent().getIntellect()), 312,  193);
            font12.draw(batch, String.valueOf(GameCharacter.currentlyChosen.getStatComponent().getSpirit()), 312,  158);
            font12.draw(batch, String.valueOf(GameCharacter.currentlyChosen.getStatComponent().getStrength()), 312,  123);
            font12.draw(batch, String.valueOf(GameCharacter.currentlyChosen.getStatComponent().getAgility()), 312,  88);
            font12.draw(batch, String.valueOf(GameCharacter.currentlyChosen.getStatComponent().getLight()), 312, 53);
        }

    }

    private void drawGrayBackground() {
        shapeRenderer.setColor(Color.DARK_GRAY);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        // Gray background
        shapeRenderer.rect(0, 0, 1280, 230);
        shapeRenderer.end();
    }
}
