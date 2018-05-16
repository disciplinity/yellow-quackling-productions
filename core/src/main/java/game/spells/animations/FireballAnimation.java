package game.spells.animations;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import game.actors.GameCharacter;

public class FireballAnimation extends SpellAnimation {


    public FireballAnimation() {
        super();
    }

//    private static ShapeRenderer commonShapeRenderer = new ShapeRenderer();


    public void draw(Batch batch) {

        if (!started) {
            started = true;
            pe.getEmitters().first().setPosition(spellStartX, spellStartY);
            pe.start();
            spellSound.play(1.0f);
        }
        pe.update(Gdx.graphics.getDeltaTime());
        pe.setPosition(spellVelocityX, spellVelocityY);

        pe.draw(batch);
        if (spellVelocityX >= spellEndX) {
            pe.reset();
            caster.setCastingSpell(false);
            spellVelocityX = spellStartX;
            spellVelocityY = spellStartY;
            started = false;
        }

        spellVelocityX += 15;
        spellVelocityY += (spellEndY - spellStartY) / 50;
    }


}
