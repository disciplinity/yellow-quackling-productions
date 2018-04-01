package main.java.ui.combat;


import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Group;
import main.java.game.actors.GameCharacter;
import main.java.game.components.EquipmentComponent;
import main.java.game.components.GraphicsComponent;
import main.java.game.constants.TexturePaths;
import main.java.game.items.Item;
import main.java.game.items.ItemSlot;
import java.util.List;


public class GearGroup extends Group {

    private Texture leonardo;
    private TextureRegion[][] items;
    private static ItemSlot[] itemSlots;
    private final int TOTAL_ITEM_SLOTS = 7;
    private static ShapeRenderer sr;

    private static GearGroup reference;


    public GearGroup(ShapeRenderer sr) {
        reference = this;
        leonardo = new Texture(TexturePaths.LEONARDO);
        itemSlots = new ItemSlot[TOTAL_ITEM_SLOTS];

        items = new GraphicsComponent("items.png", 13, 7, 476, 246, 13).getTextureRegions();
        this.sr = sr;

        createItemSlots();
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(leonardo, 15, 15, 250, 200);

        batch.end();

            for (ItemSlot itemSlot : itemSlots) {
                itemSlot.draw(batch, parentAlpha);
            }

        batch.begin();

    }

    private void createItemSlots() {
        itemSlots[0] = new ItemSlot(115, 150); // helm
        itemSlots[1] = new ItemSlot(50, 130); // main
        itemSlots[2] = new ItemSlot(180, 130); // off
        itemSlots[3] = new ItemSlot(115, 85); // chest
        itemSlots[4] = new ItemSlot(50, 20); // gloves
        itemSlots[5] = new ItemSlot(180, 20); // boots
        itemSlots[6] = new ItemSlot(115, 20); // legs
        for (ItemSlot itemSlot : itemSlots) {
            itemSlot.setSr(sr); // we are reusing our current shaperenderer, because it's an expensive object
            this.addActor(itemSlot);
        }
    }

    /**
     * It is very important that at the same indexes itemslots and items would match. That means, that at index 0 there should be helm, and helm slot.
     */
    public static void fillItemSlots() {

        if (GameCharacter.currentlyChosen.getGraphicsComponent().isOpponent()) return;

        EquipmentComponent ec = GameCharacter.currentlyChosen.getEquipmentComponent();
        List<Item> items = ec.getItemSet().getAllItems();
        int c = 0;
        for (ItemSlot is : itemSlots) {
            Item item = items.get(c);
            if (is.getItem() != null && is.getItem() != item ) {
                reference.removeActor(is.getItem());
            }

            if (item != null) {
                item.setIs(is);
                item.setSr(sr);
                reference.addActor(item);
            }
            is.setItem(item);
            c++;
        }
    }


}
