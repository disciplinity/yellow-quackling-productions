package ui.combat;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Group;
import game.actors.GameCharacter;
import game.components.GraphicsComponent;

public class UIBarGroup extends Group {

    private ShapeRenderer shapeRenderer;
    private Texture cardTexture;
    private TextureRegion[][] elements;
    private GearGroup gearGroup;
    private SpellGroup spellGroup;
    private UIDrawer drawer;

    public UIBarGroup() {
        // TODO: Graphic component with EXCESS parameters(eg for animation purposes) -> Change something or create new reduced constructor
        cardTexture = new Texture("screens/card.jpg");
        elements = new GraphicsComponent("screens/elements.png", 3, 2, 100, 100, 5).getTextureRegions();
        drawer = new UIDrawer();
        shapeRenderer = new ShapeRenderer();
        shapeRenderer.setAutoShapeType(true);

        gearGroup = new GearGroup(shapeRenderer);
        this.addActor(gearGroup);

        spellGroup = new SpellGroup(shapeRenderer);
        this.addActor(spellGroup);

    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
//        drawer.drawGrayBackground(batch);

        batch.end();
        drawer.drawGrayBackground(shapeRenderer);
        batch.begin();
        drawer.drawElements(batch, elements);
        drawer.drawCards(batch, cardTexture);


        if (friendlyCharacterIsChosen()) {
            drawer.drawStats(batch);
            gearGroup.draw(batch, parentAlpha);
            spellGroup.draw(batch, parentAlpha);

        }
    }

    private boolean friendlyCharacterIsChosen() {
        return GameCharacter.currentlyChosen != null && !GameCharacter.currentlyChosen.getGraphicsComponent().isOpponent();
    }
}
