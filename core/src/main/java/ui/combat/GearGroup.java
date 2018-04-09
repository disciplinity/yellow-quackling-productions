package ui.combat;


import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Group;
import game.actors.GameCharacter;
import game.actors.Slot;
import game.components.EquipmentComponent;
import game.components.GraphicsComponent;
import game.constants.TexturePaths;
import game.items.Item;
import game.items.ItemSlot;
import java.util.List;


public class GearGroup extends Group {

    private Texture leonardo;
    private TextureRegion[][] items;
    private static Slot[] itemSlots;
    private static ShapeRenderer sr;

    private static GearGroup reference;
    private final int ITEM_SLOT_WIDTH = 50;
    private final int ITEM_SLOT_HEIGHT = 50;
    private final int TOTAL_ITEM_SLOTS = 7;


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

        drawBorderAroundLeonardo();
        for (Slot itemSlot : itemSlots) {
            itemSlot.draw(batch, parentAlpha);
        }

        batch.begin();


    }

    private void drawBorderAroundLeonardo() {
        sr.begin(ShapeRenderer.ShapeType.Line);
        sr.setColor(Color.BLACK);
        sr.rect(15, 15, 250, 200);
        sr.end();
    }

    private void createItemSlots() {
        itemSlots[0] = new ItemSlot(115, 150, ITEM_SLOT_WIDTH, ITEM_SLOT_HEIGHT); // helm
        itemSlots[1] = new ItemSlot(50, 130, ITEM_SLOT_WIDTH, ITEM_SLOT_HEIGHT); // main
        itemSlots[2] = new ItemSlot(180, 130, ITEM_SLOT_WIDTH, ITEM_SLOT_HEIGHT); // off
        itemSlots[3] = new ItemSlot(115, 85, ITEM_SLOT_WIDTH, ITEM_SLOT_HEIGHT); // chest
        itemSlots[4] = new ItemSlot(50, 20, ITEM_SLOT_WIDTH, ITEM_SLOT_HEIGHT); // gloves
        itemSlots[5] = new ItemSlot(180, 20, ITEM_SLOT_WIDTH, ITEM_SLOT_HEIGHT); // boots
        itemSlots[6] = new ItemSlot(115, 20, ITEM_SLOT_WIDTH, ITEM_SLOT_HEIGHT); // legs
        for (Slot itemSlot : itemSlots) {
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
        for (Slot is : itemSlots) {
            Item item = items.get(c);
            if (is.getActorOnThisSlot() != null && is.getActorOnThisSlot() != item ) {
                reference.removeActor(is.getActorOnThisSlot());
            }

            if (item != null) {
                item.setIs((ItemSlot) is);
                item.setSr(sr);
                reference.addActor(item);
            }
            is.setActorOnThisSlot(item);
            c++;
        }
    }


}
