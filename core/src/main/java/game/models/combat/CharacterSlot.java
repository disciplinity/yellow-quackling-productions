package main.java.game.models.combat;

import main.java.game.actors.GameCharacter;
import lombok.Data;

@Data
class CharacterSlot {

    private final int x;
    private final int y;
    private GameCharacter actor;


    CharacterSlot(int x, int y) {
        this.x = x;
        this.y = y + 230; // 230 is the height of UI bar. This way we'll assume that y = 0 is where UI bar ends.
    }

    public void putActor(GameCharacter actor) {
        this.actor = actor;
    }

}