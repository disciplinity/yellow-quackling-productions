package main.java.game.actors;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import main.java.database.DBConnector;
import main.java.game.components.EquipmentComponent;
import main.java.game.components.GraphicsComponent;
import main.java.game.components.StatComponent;
import main.java.game.items.Item;
import main.java.game.items.ItemInfo;
import main.java.game.items.ItemSet;
import main.java.game.models.combat.CombatSetup;

import static main.java.game.constants.TexturePaths.ICEMAGE_IDLE_TEXTURE;
import static main.java.game.constants.TexturePaths.KNIGHT_IDLE_TEXTURE;

public class CharacterFactory {

    static TextureRegion[][] items = new GraphicsComponent("items.png", 13, 7, 476, 246, 13).getTextureRegions();


    public static CombatSetup createCombatGroupMock1() {
        GameCharacter a1 = createMockIceMage();
        GameCharacter a2 = createMockIceMage();
        GameCharacter a3 = createMockKnight();
        return new CombatSetup(a1, a2, a3);
    }

    public static CombatSetup createCombatGroupMock2() {
        GameCharacter a1 = createMockKnight();
        GameCharacter a2 = createMockIceMage();
        GameCharacter a3 = createMockKnight();
        return new CombatSetup(a1, a2, a3);
    }

    public static CombatSetup createCombatGroupExample2() {
        // TODO: should be DBConnector.fetchCombatSetup(playerId) -> model to store heroId at slot 1, 2 & 3 with it's stats OR do 3 different request to the DB server
        GameCharacter a1 = createKnight(0); // if 2nd variant(3 diff request to DBserver):
        //                                  createHero(playerId); and method decides which hero to create either it should be mage or knight
        GameCharacter a2 = createKnight(0);
        GameCharacter a3 = createIceMage(1);
        return new CombatSetup(a1, a2, a3);
    }

    //TODO: MOAAAR COMPONENTS! BETTER CONSTRUCTORS!!! VOTE KALJULAID!
    // Here argument should be already fetched stats, because they should be fetched for 3 heroes at once from the database
    // TODO: Flow should be the following:
    // TODO: 1. Fetch data of the pack of 3 combat heroes of the player within his/her id from the database
    // TODO: 2. Send data to the method that determines which hero should be created and create it with proper stats.
    // To do this db should fetch data to some object, that could store variable for hero id (1 for mage, 2 for knight etc.)
    //and be able to connect this id to stats (so hero id and it's stats in one model, in this case our created StatComponent doesn't fit)
    public static GameCharacter createIceMage(int playerId) {

        GraphicsComponent graphicsComponent = new GraphicsComponent(ICEMAGE_IDLE_TEXTURE,
                10, 1, 220, 200, 10);

        return new GameCharacter(createStatComponentFromFetchedStats(playerId), graphicsComponent);
    }

    public static GameCharacter createMockIceMage() {



        GraphicsComponent graphicsComponent = new GraphicsComponent(ICEMAGE_IDLE_TEXTURE,
                10, 1, 220, 200, 10);

        EquipmentComponent equipmentComponent = createMockEquipmentComponentForMage();

        return new GameCharacter(new StatComponent(35, 8, 11), graphicsComponent, equipmentComponent);
    }

    public static GameCharacter createMockKnight() {

        GraphicsComponent graphicsComponent = new GraphicsComponent(KNIGHT_IDLE_TEXTURE,
                6, 1, 170, 115, 6);

        EquipmentComponent equipmentComponent = createMockEquipmentComponentForKnight();

        return new GameCharacter(new StatComponent(12, 22, 16), graphicsComponent, equipmentComponent);
    }

    public static GameCharacter createKnight(int playerId) {

        GraphicsComponent graphicsComponent = new GraphicsComponent(KNIGHT_IDLE_TEXTURE,
                6, 1, 146, 102, 6);

        return new GameCharacter(new StatComponent(10, 10, 10), graphicsComponent);
    }

    private static StatComponent createStatComponentFromFetchedStats(int playerId) {
        return new DBConnector().fetchStats(playerId);
    }

    private static EquipmentComponent createMockEquipmentComponentForMage() {
        ItemInfo helmInfo = new ItemInfo("Helm of the Fallen", "\nDefense: +5\nResist: +2%", items[5][1]);
        ItemInfo mainHandInfo = new ItemInfo("Dagger of Destruction", "\nMagic Attack: 12-17\nCrit chance: +5%", items[0][8]);
        ItemInfo offHandInfo = new ItemInfo("Swampy didgeridoo", "\nStamina: +20\nHP regen: +1%", items[0][9]);
        ItemInfo chestInfo = new ItemInfo("Armor of the Champions", "\nDefense: +10\nResist: +5%", items[2][1]);
        ItemInfo glovesInfo = new ItemInfo("Mystic Gloves", "\nDefense: +3\nResist: +1%", items[1][1]);
        ItemInfo bootsInfo = new ItemInfo("Ak'heia boots", "\nDefense: +5\nResist: +0%\nSpeed: +5%", items[4][1]);
        ItemInfo legsInfo = new ItemInfo("Yirog's sleeves", "\nDefense: +1\nResist: +10%\nImmune to ice damage", items[6][6]);

        Item helm = new Item(helmInfo);
        Item mainHand = new Item(mainHandInfo);
        Item offHand = new Item(offHandInfo);
        Item chest = null; //new Item(chestInfo);
        Item gloves = new Item(glovesInfo);
        Item boots = new Item(bootsInfo);
        Item legs = null; //new Item(legsInfo);

        ItemSet itemSet = new ItemSet(helm, mainHand, offHand, chest, gloves, boots, legs);

        return new EquipmentComponent(itemSet);
    }

    private static EquipmentComponent createMockEquipmentComponentForKnight() {
        ItemInfo helmInfo = new ItemInfo("Steel helm of Percevalt", "\nDefense: +30\nResist: +5%\nImmune to stun effects", items[1][0]);
        ItemInfo mainHandInfo = new ItemInfo("Conqueror", "\nAttack: 30-32\nParry rate: +1%", items[4][8]);
        ItemInfo offHandInfo = new ItemInfo("Molten Hellscreamer", "\nFiery damage: +10%\nStrength: +15", items[3][12]);
        ItemInfo chestInfo = new ItemInfo("Ephemeral cuirass", "\nDefense: +0\nResist: +10%\nAbsorb chance: 2%", items[6][2]);
        ItemInfo glovesInfo = new ItemInfo("Sunholders", "\nDefense: +1\nFire Resist: +15%", items[5][0]);
        ItemInfo bootsInfo = new ItemInfo("Blaze runners", "\nDefense: +1\nFire Resist: +5%\nCan walk on fire", items[3][3]);
        ItemInfo legsInfo = new ItemInfo("Bone-enchanted leggings", "\nCan't be targeted by\nunholy magic", items[1][5]);

        Item helm = new Item(helmInfo);
        Item mainHand = null; //new Item(mainHandInfo);
        Item offHand = new Item(offHandInfo);
        Item chest = new Item(chestInfo);
        Item gloves = null; //new Item(glovesInfo);
        Item boots = new Item(bootsInfo);
        Item legs = new Item(legsInfo);

        ItemSet itemSet = new ItemSet(helm, mainHand, offHand, chest, gloves, boots, legs);

        return new EquipmentComponent(itemSet);
    }
}
