package network.server;

import network.manager.PlayerCombatInfo;

class Room {
    private static int roomCounter = 0;
    private int id = roomCounter++;
    private GameServer.GameConnection firstPlayerConnection;
    private GameServer.GameConnection secondPlayerConnection;
    private PlayerCombatInfo firstPlayerCombatInfo;
    private PlayerCombatInfo secondPlayerCombatInfo;

    Room(GameServer.GameConnection connection) {
        this.firstPlayerConnection = connection;
    }

    int getId() {
        return id;
    }

    boolean isEmpty() {
        return secondPlayerConnection == null;
    }

    public GameServer.GameConnection getFirstPlayerConnection() {
        return firstPlayerConnection;
    }

    public GameServer.GameConnection getSecondPlayerConnection() {
        return secondPlayerConnection;
    }


    public void setSecondPlayerConnection(GameServer.GameConnection secondPlayerConnection) {
        this.secondPlayerConnection = secondPlayerConnection;
    }

    public PlayerCombatInfo getFirstPlayerCombatInfo() {
        return firstPlayerCombatInfo;
    }

    public PlayerCombatInfo getSecondPlayerCombatInfo() {
        return secondPlayerCombatInfo;
    }

    public void setFirstPlayerCombatInfo(PlayerCombatInfo firstPlayerCombatInfo) {
        this.firstPlayerCombatInfo = firstPlayerCombatInfo;
    }

    public void setSecondPlayerCombatInfo(PlayerCombatInfo secondPlayerCombatInfo) {
        this.secondPlayerCombatInfo = secondPlayerCombatInfo;
    }


}
