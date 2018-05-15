package game.models.combat;

import game.actors.GameCharacter;
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

    public CombatSetup(GameCharacter[] heroes) {
        combatSetup.add(heroes[0]);
        combatSetup.add(heroes[1]);
        combatSetup.add(heroes[2]);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (GameCharacter c:
        combatSetup) {
            sb.append(c.getName()).append(" ");
        }
        return sb.toString();
    }

}
