package game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import lombok.Getter;
import lombok.Setter;

public abstract class Slot extends Actor{

    @Setter
    protected boolean hovered;

    @Getter
    private Actor actorOnThisSlot;
    private final int SLOT_WIDTH;
    private final int SLOT_HEIGHT;
    private int x, y;

    @Setter
    protected ShapeRenderer sr;

    public Slot(int x, int y, int width, int height) {
        this.x = x;
        this.y = y;
        this.SLOT_WIDTH = width;
        this.SLOT_HEIGHT = height;
        this.setBounds(x, y, SLOT_WIDTH, SLOT_HEIGHT);

        super.addListener(new InputListener() {
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

    public void setActorOnThisSlot(Actor actor) {
        this.actorOnThisSlot = actor;
        if (actor != null) {
            actor.setPosition(x, y);
            actor.setBounds(x, y, SLOT_WIDTH, SLOT_HEIGHT);
        }
    }

    public abstract void draw(Batch batch, float parentAlpha);
}