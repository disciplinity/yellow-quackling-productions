package game.spells.animations;

import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.scenes.scene2d.Actor;
import game.actors.GameCharacter;
import lombok.Getter;
import lombok.Setter;

public abstract class SpellAnimation extends Actor {

    @Getter
    protected ParticleEffect pe;
    @Getter
    protected boolean started = false;
    @Setter
    @Getter
    protected boolean isCastingSpell = false;


    @Setter @Getter
    protected float spellStartX;
    @Setter @Getter
    protected float spellStartY;
    @Setter
    protected float spellVelocityX;
    @Setter
    protected float spellVelocityY;
    @Setter @Getter
    protected float spellEndX;
    @Setter @Getter
    protected float spellEndY;

    @Setter @Getter
    protected Sound spellSound;
    @Setter @Getter
    protected GameCharacter caster;

    public SpellAnimation() {
        pe = new ParticleEffect();

    }

    public abstract void draw(Batch batch);


}
