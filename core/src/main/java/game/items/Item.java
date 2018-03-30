package main.java.game.items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;
import main.java.game.constants.TexturePaths;

@AllArgsConstructor
@Data
public class Item extends Actor {

    private String name;
    private String description;
    private Texture texture;
    private boolean hovered;
    @Setter
    private ItemSlot is;
    private TextureRegion txr;
    private ShapeRenderer sr;

    public Item(String name, String description, TextureRegion txr) {
        this.name = name;
        this.description = description;
        this.txr = txr;
        hovered = false;
        texture = new Texture(TexturePaths.LEONARDO);
        sr = new ShapeRenderer();

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
        batch.draw(txr, is.getX(), is.getY(), is.getWidth(), is.getHeight());
        batch.end();
        if (hovered) {
            sr.begin(ShapeRenderer.ShapeType.Filled);
            sr.setColor(Color.BLACK);
            sr.rect(getX(), getY() + 100, 200, 200);
            sr.end();
//            batch.draw(texture, getX() + 25 ,getY() + 65, 100, 200);
        }
    }

}
