package network.manager;

import lombok.Data;
import network.database.entity.HeroSetupEntity;

import java.util.List;

@Data
public class PlayerCombatInfo {
    private String username;
    private List<HeroSetupEntity> heroes;

    public PlayerCombatInfo() {}

    public PlayerCombatInfo(String username, List<HeroSetupEntity> heroes) {
        this.username = username;
        this.heroes = heroes;
    }
}
