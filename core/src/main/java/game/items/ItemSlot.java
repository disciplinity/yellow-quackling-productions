package game.items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
public class ItemSlot extends Actor {

    @Getter
    private Item item;
    @Setter
    private boolean hovered;
    private final int ITEM_SLOT_WIDTH = 50;
    private final int ITEM_SLOT_HEIGHT = 50;

    private int x, y;

    @Setter
    private ShapeRenderer sr;



    public ItemSlot(int x, int y) {
        hovered = false;
        this.x = x;
        this.y = y;
        this.setBounds(x, y, ITEM_SLOT_WIDTH, ITEM_SLOT_HEIGHT);

        this.addListener(new InputListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                hovered = true;
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                hovered = false;
            }
        });
        Gdx.gl.glLineWidth(2);

    }

    public void setItem(Item item) {
        this.item = item;
        if (item != null) {
            item.setPosition(x, y);
            item.setBounds(x, y, ITEM_SLOT_WIDTH, ITEM_SLOT_HEIGHT);
        }
    }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        sr.begin(ShapeRenderer.ShapeType.Line);
        sr.setColor(hovered ? Color.GOLD : Color.BLACK);
        sr.rect(this.getX(), this.getY(), 50, 50);
        sr.end();
        if (item != null) {
            item.draw(batch, parentAlpha);
        }
    }
}
