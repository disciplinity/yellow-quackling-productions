//package server;
//
//import com.esotericsoftware.kryo.Kryo;
//import com.esotericsoftware.kryonet.Client;
//import com.esotericsoftware.kryonet.Server;
//import lombok.AllArgsConstructor;
//
//import java.util.Random;
//
//@AllArgsConstructor
//public class Registrator {
//
//    private Server server;
//    private Client client;
//
//
//
//    public void register() {
//        Kryo kryo1 = server.getKryo();
//        kryo1.register(SomeRequest.class);
//        kryo1.register(SomeResponse.class);
//        Kryo kryo2 = client.getKryo();
//        kryo.register(SomeRequest.class);
//        kryo.register(SomeResponse.class);
//    }
//}
