package actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import com.badlogic.gdx.scenes.scene2d.actions.MoveByAction;

public class IceMage extends Actor {

    private TextureRegion textureRegion;
    private Texture texture;

    public IceMage() {
        texture = new Texture("0.png");
        textureRegion = new TextureRegion(texture);
        this.setPosition(0, 0);
        this.setSize(texture.getWidth(), texture.getHeight());



   }


    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.setColor(1, 1, 1, parentAlpha);

        batch.draw(texture, this.getX(), this.getY(), this.getWidth(), this.getHeight());

    }


}
