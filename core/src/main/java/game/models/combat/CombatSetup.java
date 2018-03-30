package main.java.game.models.combat;

import main.java.game.actors.GameCharacter;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class CombatSetup {
    @Getter
    private List<GameCharacter> combatSetup = new ArrayList<>();

    public CombatSetup(GameCharacter hero0, GameCharacter hero1, GameCharacter hero2) {
        combatSetup.add(hero0);
        combatSetup.add(hero1);
        combatSetup.add(hero2);
    }

}
