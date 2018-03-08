package game.factory;

import game.actors.IceMage;
import game.actors.Knight;
import com.badlogic.gdx.scenes.scene2d.Actor;
import game.components.StatComponent;
import game.models.CombatSetup;

public class CombatSetupTestFactory {

    public static CombatSetup createCombatGroupExample1() {
        Actor a1 = new IceMage(new StatComponent(100, 10, 10, 10, 50));
        Actor a2 = new IceMage(new StatComponent(10, 100, 10, 50, 10));
        Actor a3 = new IceMage(new StatComponent(10, 10, 50, 10, 100));
        return new CombatSetup(a1, a2, a3);
    }

    public static CombatSetup createCombatGroupExample2() {
        Actor a1 = new Knight(new StatComponent(99, 10, 10, 10, 50));
        Actor a2 = new Knight(new StatComponent(9, 101, 10, 50, 10));
        Actor a3 = new Knight(new StatComponent(9, 10, 50, 11, 100));
        return new CombatSetup(a1, a2, a3);
    }
}
