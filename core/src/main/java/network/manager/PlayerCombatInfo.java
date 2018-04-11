package network.manager;

import lombok.Data;

@Data
public class PlayerCombatInfo {
    private String username;
    private Hero[] heroes;

    public PlayerCombatInfo(String username, Hero hero1, Hero hero2, Hero hero3) {
        this.username = username;
        this.heroes = new Hero[]{hero1, hero2, hero3};
    }
}
