package game.items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import game.actors.Slot;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

public class ItemSlot extends Slot {


    public ItemSlot(int x, int y, int width, int height) {
        super(x, y, width, height);
        hovered = false;

    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        sr.begin(ShapeRenderer.ShapeType.Line);
        sr.setColor(hovered ? Color.GOLD : Color.BLACK);
        sr.rect(this.getX(), this.getY(), getWidth(), getHeight());
        sr.end();
        if (getActorOnThisSlot() != null) {
            getActorOnThisSlot().draw(batch, parentAlpha);
        }
    }
}
