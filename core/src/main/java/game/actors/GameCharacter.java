package game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import game.components.SpellBookComponent;
import game.models.combat.HealthBar;
import game.spells.Spell;
import game.spells.animations.FireballAnimation;
import game.spells.animations.SpellAnimation;
import lombok.Setter;
import game.components.EquipmentComponent;
import game.components.StatComponent;
import game.components.GraphicsComponent;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class GameCharacter extends Actor {

    @Setter @Getter
    private String name;

    @Getter @Setter
    private double health;

    @Getter
    private EquipmentComponent equipmentComponent;
    @Getter
    private StatComponent statComponent;
    @Getter
    private GraphicsComponent graphicsComponent;
    @Getter
    private SpellBookComponent spellBookComponent;



//    @Getter
//    private HealthBar healthBar;

    @Setter @Getter
    private int slotId;

    public static GameCharacter currentlyChosen = null;
    @Setter @Getter
    private boolean isCastingSpell = false;
//    @Setter @Getter
//    private float spellStartX;
//    @Setter @Getter
//    private float spellStartY;
//
//    @Setter @Getter
//    private float spellEndX;
//    @Setter @Getter
//    private float spellEndY;

//    private static ShapeRenderer commonShapeRenderer = new ShapeRenderer();


    public GameCharacter(String name, StatComponent statComponent, GraphicsComponent graphicsComponent, EquipmentComponent equipmentComponent, SpellBookComponent spellBookComponent) {
        this.name = name;
        this.health = 100;

        this.setSize(graphicsComponent.getSizeWidth(), graphicsComponent.getSizeHeight());
        this.statComponent = statComponent;
        this.graphicsComponent = graphicsComponent;
        this.equipmentComponent = equipmentComponent;
        this.spellBookComponent = spellBookComponent;

//        prepareFireballAnimation();
        spellBookComponent.getSpellSet().getAllSpells().get(0).setSpellAnimation(new FireballAnimation());



        this.addListener(graphicsComponent.getTouchListener(this));
//        this.healthBar = new HealthBar(commonShapeRenderer, getX(), getY() + getHeight() + 15);


    }

    public GameCharacter(StatComponent statComponent, GraphicsComponent graphicsComponent, EquipmentComponent equipmentComponent) {
        this.setSize(graphicsComponent.getSizeWidth(), graphicsComponent.getSizeHeight());
        this.statComponent = statComponent;
        this.graphicsComponent = graphicsComponent;
        this.equipmentComponent = equipmentComponent;
        this.addListener(graphicsComponent.getTouchListener(this));

    }

    public GameCharacter(StatComponent statComponent, GraphicsComponent graphicsComponent) {
        this.setSize(graphicsComponent.getSizeWidth(), graphicsComponent.getSizeHeight());
        this.statComponent = statComponent;
        this.graphicsComponent = graphicsComponent;

        // TODO: Ну чисто теоретически. Эта Модель хранит в себе листэнер. Всё законно. Его назначает контроллер, и используется он во вью. Sounds kind of legal.
        this.addListener(graphicsComponent.getTouchListener(this));
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.end();
        batch.begin();

        if (isCastingSpell) {
            castSpell(batch);



        }
        graphicsComponent.draw(batch, parentAlpha, getX(), getY(), getWidth(), getHeight());
    }

    public void castSpell(Batch batch) {
        spellBookComponent.getSpellSet().getAllSpells().get(0).getSpellAnimation().draw(batch);

    }

//    private void prepareFireballAnimation() {
//
//        for (int i = 0; i < spellBookComponent.getSpellSet().getAllSpells().size(); i++) {
//            Spell fireBall = spellBookComponent.getSpellSet().getAllSpells().get(i);
//            if (fireBall == null) continue;
//            fireBall.setSpellAnimation(new FireballAnimation());
//            SpellAnimation fireballAnimation = fireBall.getSpellAnimation();
//
//            fireballAnimation.setSpellStartX(getX() + getWidth());
//            fireballAnimation.setSpellStartY(getY() + (getHeight() / 2) + 210);
//            fireballAnimation.setSpellVelocityX(fireballAnimation.getSpellStartX());
//            fireballAnimation.setSpellVelocityY(fireballAnimation.getSpellStartY());
//            fireballAnimation.setSpellSound(Gdx.audio.newSound(Gdx.files.internal("sounds/fireball_sound.wav")));
//            fireballAnimation.getPe().load(Gdx.files.internal("particles/fireball_particle"), Gdx.files.internal(""));
//
//
//        }
//
//
//
//    }
//



}
