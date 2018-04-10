package game.spells;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import game.actors.Slot;
import game.components.SpellBookComponent;

public class SpellSlot extends Slot {


    public SpellSlot(int x, int y, int width, int height) {
        super(x, y, width, height);

        this.addListener(new ClickListener() {

            @Override
            public void clicked(InputEvent event, float x, float y) {
                if (SpellBookComponent.currentSpellChosen != null) {
                    SpellBookComponent.currentSpellChosen.setClicked(false);
                    SpellBookComponent.currentSpellChosen = null;
                }
            }
        });

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {


        sr.begin(ShapeRenderer.ShapeType.Line);
        sr.setColor(Color.valueOf("d6bd40"));
        sr.rect(this.getX(), this.getY(), getWidth(), getHeight());
        sr.set(ShapeRenderer.ShapeType.Filled);
        sr.setColor(hovered ? Color.valueOf("706f6a") : Color.DARK_GRAY);
        sr.rect(this.getX() + 1, this.getY() + 1, this.getWidth() - 2, this.getHeight() - 2);
        sr.end();
        if (getActorOnThisSlot() != null) {
            getActorOnThisSlot().draw(batch, parentAlpha);
        }
    }
}
