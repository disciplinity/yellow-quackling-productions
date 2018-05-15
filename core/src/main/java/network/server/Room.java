package network.server;

import network.logic.CombatLogic;
import network.manager.PlayerCombatInfo;

class Room {
    private static int roomCounter = 0;
    private int id = roomCounter++;
    private GameServer.GameConnection firstPlayerConnection, secondPlayerConnection;
    private PlayerCombatInfo firstPlayerCombatInfo, secondPlayerCombatInfo;
    private CombatLogic combatLogic = new CombatLogic();

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

    public GameServer.GameConnection getOpponentConnection(GameServer.GameConnection connection) {
        return connection.equals(firstPlayerConnection) ? secondPlayerConnection : firstPlayerConnection;
    }

    public boolean isPlayersTurn(GameServer.GameConnection connection) {
        return (connection.equals(firstPlayerConnection) && combatLogic.isPlayerOneTurn()) || (connection.equals(secondPlayerConnection) && !combatLogic.isPlayerOneTurn());
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
        this.combatLogic.setPlayerOneCombatSetup(firstPlayerCombatInfo);
    }

    public void setSecondPlayerCombatInfo(PlayerCombatInfo secondPlayerCombatInfo) {
        this.secondPlayerCombatInfo = secondPlayerCombatInfo;
        this.combatLogic.setPlayerTwoCombatSetup(secondPlayerCombatInfo);
    }


    public CombatLogic getCombatLogic() {
        return combatLogic;
    }
}
