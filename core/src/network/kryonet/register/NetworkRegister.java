package network.kryonet.register;


import actors.IceMage;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;
import components.GraphicsComponent;
import components.StatComponent;
import models.CombatGroup;


// This class is a convenient place to keep things common to both the client and server.
public class NetworkRegister {
    static public final int port = 54555;

    // TODO: We should not pass that much objects D': :'D
    // TODO: We have to think through how to pass actor not as object. May be as JSON. sad..
    // This registers objects that are going to be sent over the network.
    static public void register (EndPoint endPoint) {
        Kryo kryo = endPoint.getKryo();
        kryo.register(RegisterName.class);
        kryo.register(String[].class);
        kryo.register(UpdateNames.class);
        kryo.register(OpponentFound.class);
        kryo.register(RegisterPlayerCombatGroup.class);
        kryo.register(CombatGroup.class);
        kryo.register(Actor[].class);
        kryo.register(IceMage.class);
        kryo.register(Array.class);
        kryo.register(Object[].class);
        kryo.register(GraphicsComponent.class);
        kryo.register(StatComponent.class);
        kryo.register(TextureRegion.class);
        kryo.register(Animation.class);
    }
    static public class RegisterPlayerCombatGroup {
        public CombatGroup combatGroup;
    }

    static public class RegisterName {
        public String name;
    }

    static public class UpdateNames {
        public String[] names;
    }

    static public class OpponentFound {
        public CombatGroup combatGroup;
    }
}