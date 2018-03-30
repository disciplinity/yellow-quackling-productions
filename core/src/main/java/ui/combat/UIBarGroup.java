package main.java.ui.combat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Group;
import main.java.game.actors.GameCharacter;
import main.java.game.components.GraphicsComponent;

public class UIBarGroup extends Group {

    private GraphicsComponent graphicsComponent;
    private ShapeRenderer shapeRenderer = new ShapeRenderer();
    private Texture leonardo = new Texture("screens/leonardo.jpeg");
    private Texture cardTexture = new Texture("screens/card.jpg");
    private TextureRegion[][] elements;

    private UIDrawer drawer;

    public UIBarGroup() {
        // TODO: Graphic component with EXCESS parameters(eg for animation purposes) -> Change something or create new reduced constructor
        graphicsComponent = new GraphicsComponent("screens/elements.png", 3, 2, 100, 100, 5);
        elements = graphicsComponent.getTextureRegions();
        drawer = new UIDrawer();
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.end();
        drawer.drawGrayBackground(shapeRenderer);
        batch.begin();

        batch.draw(leonardo, 50, 20, 200, 190);
        drawer.drawElements(batch, elements);
        drawer.drawCards(batch, cardTexture);

        if (GameCharacter.currentlyChosen != null) {
            drawer.drawStats(batch);
        }

    }





}
