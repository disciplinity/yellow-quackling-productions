package main.java.game.items;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Setter;
import main.java.game.actors.GameCharacter;
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

    private FreeTypeFontGenerator generator;
    private FreeTypeFontGenerator.FreeTypeFontParameter parameter;
    private BitmapFont font12;

    public Item(String name, String description, TextureRegion txr) {
        this.name = name;
        this.description = description;
        this.txr = txr;
        hovered = false;
        texture = new Texture(TexturePaths.LEONARDO);
        sr = new ShapeRenderer();

        generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Raleway-Medium.ttf"));
        parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 14;
        font12 = generator.generateFont(parameter);

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
            drawTextBox();
            writeTextToTextBox(batch);
        }
    }

    private void drawTextBox() {
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(Color.BLACK);
        sr.rect(getX() + 25, getY() + 60, 200, 200);
        sr.end();
        sr.begin(ShapeRenderer.ShapeType.Line);
        sr.setColor(Color.WHITE);
        sr.rect(getX() + 25, getY() + 60, 200, 200);
        sr.end();
    }

    private void writeTextToTextBox(Batch batch) {
        batch.begin();
        font12.draw(batch, name, getX() + 30, getY() + 240);
        font12.draw(batch, description, getX() + 30, getY() + 220);
        batch.end();
    }

}
