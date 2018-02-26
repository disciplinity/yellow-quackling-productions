package server;

import com.esotericsoftware.kryo.Kryo;
import com.esotericsoftware.kryonet.Client;
import com.esotericsoftware.kryonet.Connection;
import com.esotericsoftware.kryonet.Listener;

import java.io.IOException;

public class A {

    public static void main(String[] args) throws IOException, InterruptedException {
        Client client = new Client();
        client.start();
        client.connect(5000, "localhost", 54555, 54777);

        client.addListener(new Listener() {
            public void received (Connection connection, Object object) {
                if (object instanceof SomeResponse) {
                    SomeResponse response = (SomeResponse)object;
                    System.out.println(response.text);
                }
            }
        });

        Kryo kryo = client.getKryo();
        kryo.register(SomeRequest.class);
        kryo.register(SomeResponse.class);

        SomeRequest request = new SomeRequest();
        request.text = "Here is the request";
        client.sendTCP(request);

        Thread.sleep(10000);

    }

}

class SomeRequest {
    public String text;
}

class SomeResponse {
    public String text;
}
