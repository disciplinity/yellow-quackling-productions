package network.server;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Players waiting for fight or fighting.
 */
public class PlayerPool {

    private HashMap<GameServer.GameConnection, Integer> playerToRoomId = new HashMap<>();
    private ArrayList<Room> rooms = new ArrayList<>();
    private GameServer server;

    PlayerPool(GameServer server) {
        this.server = server;
    }

    void addPlayer(GameServer.GameConnection playerConnection) {
        for (Room room : rooms) {
            if (room.isEmpty()) {
                // TODO: Advanced match making [4001]
                playerToRoomId.put(playerConnection, room.getId());
                room.setSecondPlayerConnection(playerConnection);
                server.sendBattleBeginResponse(room);
                return;
            }
        }
        rooms.add(new Room(playerConnection));
    }

    public Room getRoomById(int id) throws NetworkException {
        for (Room room : rooms) {
            if (room.getId() == id) {
                return room;
            }
        }
        throw new NetworkException("There is no such room with id " + id);
    }

    public ArrayList<Room> getRooms() {
        return rooms;
    }
}
