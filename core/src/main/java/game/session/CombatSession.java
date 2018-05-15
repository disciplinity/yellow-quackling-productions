package game.session;

import lombok.Data;

/**
 * Client side combat rules.
 * Client will know health of heroes, is it his/her turn ...
 */
@Data
public class CombatSession {
    private boolean myTurn;
    private double[] heroHealthAtSlot = new double[5];

    public CombatSession(boolean myTurn) {
        this.myTurn = myTurn;
    }
}
