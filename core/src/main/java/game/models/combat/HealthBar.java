package game.models.combat;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class HealthBar extends Actor {

    private ShapeRenderer sr;
    private float x;
    private float y;

    public HealthBar(ShapeRenderer sr, float x, float y) {
        this.sr = sr;
        this.x = x;
        this.y = y + 230;
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(Color.RED);
        sr.rect(x , y, 100, 20);
        sr.end();
    }
}
