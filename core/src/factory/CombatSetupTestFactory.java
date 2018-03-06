package factory;

import actors.IceMage;
import com.badlogic.gdx.scenes.scene2d.Actor;
import components.StatComponent;
import models.CombatSetup;

public class CombatSetupTestFactory {

    public static CombatSetup createCombatGroupExample1() {
        Actor a1 = new IceMage(new StatComponent(100, 10, 10, 10, 50));
        Actor a2 = new IceMage(new StatComponent(10, 100, 10, 50, 10));
        Actor a3 = new IceMage(new StatComponent(10, 10, 50, 10, 100));
        return new CombatSetup(a1, a2, a3);
    }

    public static CombatSetup createCombatGroupExample2() {
        Actor a1 = new IceMage(new StatComponent(99, 10, 10, 10, 50));
        Actor a2 = new IceMage(new StatComponent(9, 101, 10, 50, 10));
        Actor a3 = new IceMage(new StatComponent(9, 10, 50, 11, 100));
        return new CombatSetup(a1, a2, a3);
    }
}
