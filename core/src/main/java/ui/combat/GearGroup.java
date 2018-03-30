package main.java.ui.combat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Group;
import main.java.game.components.GraphicsComponent;
import main.java.game.constants.TexturePaths;
import main.java.game.items.Item;
import main.java.game.items.ItemSlot;

public class GearGroup extends Group {

    private Texture leonardo;
    private GraphicsComponent graphicsComponent;
    private TextureRegion[][] items;
    private ItemSlot[] itemSlots;
    private final int TOTAL_ITEM_SLOTS = 7;
    private ShapeRenderer sr;


    public GearGroup(ShapeRenderer sr) {
        leonardo = new Texture(TexturePaths.LEONARDO);
        itemSlots = new ItemSlot[TOTAL_ITEM_SLOTS];

        graphicsComponent = new GraphicsComponent("items.png", 13, 7, 476, 246, 13);
        items = graphicsComponent.getTextureRegions();
        this.sr = sr;
        setUpItemSlots();

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.begin();
        batch.draw(leonardo, 15, 15, 250, 200);
        batch.end();
        for (ItemSlot itemSlot : itemSlots) {
            itemSlot.draw(batch, parentAlpha);
        }
    }

    private void setUpItemSlots() {
        itemSlots[0] = new ItemSlot(115, 20, null, sr);
        itemSlots[1] = new ItemSlot(115, 85, new Item("Armor of the Champions", "B", items[2][1]), sr);
        itemSlots[2] = new ItemSlot(115, 150, new Item("Helm of the Fallen", "B", items[5][1]), sr);
        itemSlots[3] = new ItemSlot(50, 20, new Item("Mystic Gloves", "B", items[1][1]), sr);
        itemSlots[4] = new ItemSlot(180, 20, new Item("Ak'heia boots", "B", items[4][1]), sr);
        itemSlots[5] = new ItemSlot(50, 130, new Item("Dagger of Destruction", "B", items[0][8]), sr);
        itemSlots[6] = new ItemSlot(180, 130, new Item("Swampy didgeridoo", "B", items[0][9]), sr);

        for (ItemSlot itemSlot : itemSlots) {
            this.addActor(itemSlot);
            if (itemSlot.getItem() != null) {
                itemSlot.getItem().setIs(itemSlot);
                this.addActor(itemSlot.getItem());
            }
        }
    }




}
