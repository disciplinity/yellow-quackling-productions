package network.kryonet.register;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.EndPoint;
import models.CombatSetup;


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
        kryo.register(EnterRoomWithSetup.class);
    }

    static public class RegisterName {
        public String name;
    }

    static public class UpdateNames {
        public String[] names;
    }

    static public class EnterRoomWithSetup {
        public String globalSetupId;
    }
}