package game.models;

import com.badlogic.gdx.scenes.scene2d.Actor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

public class CombatSetup {
    @Getter
    private List<Actor> combatSetup = new ArrayList<>();

    public CombatSetup(Actor hero0, Actor hero1, Actor hero2) {
        combatSetup.add(hero0);
        combatSetup.add(hero1);
        combatSetup.add(hero2);
    }

}
