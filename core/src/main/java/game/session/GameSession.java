package game.session;

import com.badlogic.gdx.Gdx;
import game.actors.CharacterFactory;
import game.models.combat.CombatSetup;
import lombok.Getter;
import network.manager.PlayerCombatInfo;

public class GameSession {
    @Getter
    private CombatSetup playerCombatSetup;
    @Getter
    private PlayerCombatInfo playerCombatInfo;

    public static class GameSessionHolder {
        static final GameSession HOLDER_INSTANCE = new GameSession();
    }

    public static GameSession getInstance() {
        return GameSession.GameSessionHolder.HOLDER_INSTANCE;
    }

    public void setPlayerCombatSetup(PlayerCombatInfo playerInfo) {
        /* postRunnable due to textures are created */
        playerCombatInfo = playerInfo;
        Gdx.app.postRunnable(() -> this.playerCombatSetup = CharacterFactory.createCombatSetupFrom(playerInfo.getHeroes()));
    }


}
