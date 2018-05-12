package game.items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import game.utils.FontGenerator;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Item extends Actor {

    private ItemInfo itemInfo;
    private boolean hovered;

    private ItemSlot is;
    private ShapeRenderer sr;
    private FontGenerator fontGenerator;

    public Item(ItemInfo itemInfo) {

        this.itemInfo = itemInfo;
        hovered = false;


        this.addListener(new InputListener() {
            @Override
            public void enter(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                is.setHovered(true);
                hovered = true;
            }

            @Override
            public void exit(InputEvent event, float x, float y, int pointer, Actor fromActor) {
                is.setHovered(false);
                hovered = false;
            }
        });
    }



    @Override
    public void draw(Batch batch, float parentAlpha) {

        batch.begin();
        batch.draw(itemInfo.getTxr(), is.getX() + 1, is.getY() + 1, is.getWidth() - 2, is.getHeight() - 2);
        batch.end();

        if (hovered) {
            drawTextBox();
            writeTextToTextBox(batch);
        }

}

    private void drawTextBox() {
        // Main frame
        Gdx.gl.glEnable(GL20.GL_BLEND);
        Gdx.gl.glBlendFunc(GL20.GL_SRC_ALPHA, GL20.GL_ONE_MINUS_SRC_ALPHA);
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(0.788f, 0.69f, 0.478f, 0.8f);
        sr.rect(getX() + 25, getY() + 60, 300, 200);

        // Now draw border for it
        sr.set(ShapeRenderer.ShapeType.Line);
        sr.setColor(Color.valueOf("51310f"));
        sr.rect(getX() + 25, getY() + 60, 300, 200);
        sr.end();
        Gdx.gl.glDisable(GL20.GL_BLEND);
    }

    private void writeTextToTextBox(Batch batch) {
        batch.begin();

        // Name of the item
        fontGenerator.getParameter().size = 16;
        fontGenerator.getParameter().color = Color.FOREST;
        fontGenerator.getParameter().borderWidth = 3;
        fontGenerator.getParameter().borderColor = Color.BLACK;


        fontGenerator.setFont12(fontGenerator.getGenerator().generateFont(fontGenerator.getParameter()));
        fontGenerator.getFont12().draw(batch, itemInfo.getName(), getX() + 30, getY() + 240);

        // Description of the item
        fontGenerator.getParameter().size = 15;
        fontGenerator.getParameter().color = Color.valueOf("e06328");
        fontGenerator.getParameter().borderWidth = 2;

        fontGenerator.getParameter().shadowColor = Color.valueOf("682708");

        fontGenerator.setFont12(fontGenerator.getGenerator().generateFont(fontGenerator.getParameter()));
        fontGenerator.getFont12().draw(batch, itemInfo.getDescription(), getX() + 30, getY() + 220);

        batch.end();
    }

}
