package game.session;

import com.badlogic.gdx.Gdx;
import game.actors.CharacterFactory;
import game.models.combat.CombatSetup;
import network.manager.PlayerCombatInfo;

public class GameSession {
    private CombatSetup playerCombatSetup;

    public static class GameSessionHolder {
        static final GameSession HOLDER_INSTANCE = new GameSession();
    }

    public static GameSession getInstance() {
        return GameSession.GameSessionHolder.HOLDER_INSTANCE;
    }

    public void setPlayerCombatSetup(PlayerCombatInfo playerInfo) {
        /* postRunnable due to textures are created */
        Gdx.app.postRunnable(() -> this.playerCombatSetup = CharacterFactory.createCombatSetupFrom(playerInfo.getHeroes()));
    }

    public CombatSetup getPlayerCombatSetup() {
        return playerCombatSetup;
    }

}
