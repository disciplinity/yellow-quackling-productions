package main.java.ui.combat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
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
import main.java.game.items.ItemInfo;
import main.java.game.items.ItemSlot;

import java.util.ArrayList;
import java.util.List;

public class GearGroup extends Group {

    private Texture leonardo;
    private TextureRegion[][] items;
    private ItemSlot[] itemSlots;
    private final int TOTAL_ITEM_SLOTS = 7;
    private ShapeRenderer sr;
    private List<EquipmentComponent> cachedEquipmentComponents;
    private EquipmentComponent ec;


    public GearGroup(ShapeRenderer sr) {
        leonardo = new Texture(TexturePaths.LEONARDO);
        itemSlots = new ItemSlot[TOTAL_ITEM_SLOTS];
        cachedEquipmentComponents = new ArrayList<>();

        items = new GraphicsComponent("items.png", 13, 7, 476, 246, 13).getTextureRegions();
        this.sr = sr;
        setUpItemSlots();

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

    private void setUpItemSlots() {


        ItemInfo i1 = new ItemInfo("Armor of the Champions", "\nDefense: +10\nRessist: +5%", items[2][1]);
        ItemInfo i2 = new ItemInfo("Helm of the Fallen", "\nDefense: +5\nRessist: +2%", items[5][1]);
        ItemInfo i3 = new ItemInfo("Mystic Gloves", "\nDefense: +3\nRessist: +1%", items[1][1]);
        ItemInfo i4 = new ItemInfo("Ak'heia boots", "\nDefense: +5\nRessist: +0%\nSpeed: +5%", items[4][1]);
        ItemInfo i5 = new ItemInfo("Dagger of Destruction", "\nAttack: 12-17\nCrit chance: +5%", items[0][8]);
        ItemInfo i6 = new ItemInfo("Swampy didgeridoo", "\nStamina: +20\nHP regen: +1%", items[0][9]);


        itemSlots[0] = new ItemSlot(115, 20, null, sr);
        itemSlots[1] = new ItemSlot(115, 85, new Item(i1, sr), sr);
        itemSlots[2] = new ItemSlot(115, 150, new Item(i2, sr), sr);
        itemSlots[3] = new ItemSlot(50, 20, new Item(i3, sr), sr);
        itemSlots[4] = new ItemSlot(180, 20, new Item(i4, sr), sr);
        itemSlots[5] = new ItemSlot(50, 130, new Item(i5, sr), sr);
        itemSlots[6] = new ItemSlot(180, 130, new Item(i6, sr), sr);

        for (ItemSlot itemSlot : itemSlots) {
            this.addActor(itemSlot);

            if (itemSlot.getItem() != null) {
                itemSlot.getItem().setIs(itemSlot);
                this.addActor(itemSlot.getItem());
            }

        }
    }

//    private List<ItemInfo> createItemInfoList() {
//         if (!GameCharacter.currentlyChosen.getGraphicsComponent().isOpponent()) {
//
//             if (!cachedEquipmentComponents.contains(GameCharacter.currentlyChosen.getEquipmentComponent())) {
//                 cachedEquipmentComponents.
//             }
//
//         }
//    }





}
