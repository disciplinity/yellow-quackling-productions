package battle;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import lombok.Data;

@Data
class CharacterSlot {

    private final int x;
    private final int y;
    private Actor actor;


    CharacterSlot(int x, int y) {
        this.x = x;
        this.y = 230 + y; // 230 is the height of UI bar. This way we'll assume that y = 0 is where UI bar ends.
    }

    public void putActor(Actor actor) {
        this.actor = actor;
    }

    public void draw(Batch batch, float parentAlpha) {
        actor.draw(batch, parentAlpha);
    }
}
