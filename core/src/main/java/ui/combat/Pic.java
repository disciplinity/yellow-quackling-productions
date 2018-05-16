package ui.combat;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Pic extends Actor {

    private Texture texture = new Texture("heroes/knight/knight_idle.png");

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.begin();
        batch.draw(texture, 10, 10, 10, 10);
        batch.end();
    }

    // I potom v battlestagegroup   pishesh    this.addActor(new Pic());
}
