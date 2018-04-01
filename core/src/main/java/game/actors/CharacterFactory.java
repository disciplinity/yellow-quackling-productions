package main.java.game.actors;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import main.java.database.DBConnector;
import main.java.game.components.EquipmentComponent;
import main.java.game.components.GraphicsComponent;
import main.java.game.components.StatComponent;
import main.java.game.items.ItemInfo;
import main.java.game.models.combat.CombatSetup;

import static main.java.game.constants.TexturePaths.ICEMAGE_IDLE_TEXTURE;
import static main.java.game.constants.TexturePaths.KNIGHT_IDLE_TEXTURE;

public class CharacterFactory {

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

        TextureRegion[][] items = new GraphicsComponent("items.png", 13, 7, 476, 246, 13).getTextureRegions();


        GraphicsComponent graphicsComponent = new GraphicsComponent(ICEMAGE_IDLE_TEXTURE,
                10, 1, 220, 200, 10);

        return new GameCharacter(new StatComponent(35, 8, 11), graphicsComponent);
    }

    public static GameCharacter createMockKnight() {

        GraphicsComponent graphicsComponent = new GraphicsComponent(KNIGHT_IDLE_TEXTURE,
                6, 1, 170, 115, 6);

        return new GameCharacter(new StatComponent(12, 22, 16), graphicsComponent);
    }

    public static GameCharacter createKnight(int playerId) {

        GraphicsComponent graphicsComponent = new GraphicsComponent(KNIGHT_IDLE_TEXTURE,
                6, 1, 146, 102, 6);

        return new GameCharacter(new StatComponent(10, 10, 10), graphicsComponent);
    }

    private static StatComponent createStatComponentFromFetchedStats(int playerId) {
        return new DBConnector().fetchStats(playerId);
    }
}
