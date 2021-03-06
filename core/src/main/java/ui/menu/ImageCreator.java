package ui.menu;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;

public class ImageCreator extends Actor {

    Rectangle bounds;
    Sprite object;
    Texture texture;

    /**
     * Make loading pictures as objects, create button objects.
     * @param texture Image
     * @param x x-coordinate
     * @param y y-coordinate
     * @param width width of image
     * @param height height of image
     */
    public ImageCreator(Texture texture, float x, float y, float width, float height) {
        bounds = new Rectangle(x, y, width, height);
        this.texture = texture;

        setPosition(x, y);
        setSize(width, height);
        setBounds(x, y, width, height);


        this.addListener(new InputListener() {
            @Override
            public boolean touchDown(InputEvent inputEvent, float x, float y, int pointer, int button) {

                return false;
            }
        });
    }

    /**
     * Draw the button object
     * @param batch Batch
     * @param parentAlpha alpha
     */
    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(texture, getX(), getY(), getWidth(), getHeight());
    }

}
