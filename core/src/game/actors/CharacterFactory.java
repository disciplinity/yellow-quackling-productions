package game.actors;

import com.badlogic.gdx.scenes.scene2d.Actor;
import database.DBConnector;
import game.components.GraphicsComponent;
import game.components.StatComponent;
import game.models.combat.CombatSetup;

import static game.constants.TexturePaths.ICEMAGE_IDLE_TEXTURE;
import static game.constants.TexturePaths.KNIGHT_IDLE_TEXTURE;

public class CharacterFactory {

    public static CombatSetup createCombatGroupExample1() {
        GameCharacter a1 = createIceMage(1);
        GameCharacter a2 = createIceMage(1);
        GameCharacter a3 = createKnight(0);
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
    public static GameCharacter createIceMage(int playerId){

        GraphicsComponent graphicsComponent = new GraphicsComponent(ICEMAGE_IDLE_TEXTURE,
                10, 1,220, 200, 10);

        return new GameCharacter(createStatComponentFromFetchedStats(playerId), graphicsComponent);
    }

    public static GameCharacter createKnight(int playerId){

        GraphicsComponent graphicsComponent = new GraphicsComponent(KNIGHT_IDLE_TEXTURE,
                6, 1,146, 102, 6);

        return new GameCharacter(createStatComponentFromFetchedStats(playerId), graphicsComponent);
    }

    private static StatComponent createStatComponentFromFetchedStats(int playerId) {
        return new DBConnector().fetchStats(playerId);
    }
}
