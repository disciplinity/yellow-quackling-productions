package game.actors;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.ParticleEffect;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Actor;
import game.components.SpellBookComponent;
import game.models.combat.HealthBar;
import lombok.Setter;
import game.components.EquipmentComponent;
import game.components.StatComponent;
import game.components.GraphicsComponent;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class GameCharacter extends Actor {

    private double hp;
    private double force;
    //    private SpellBookComponent spellBookComponent;

    @Getter
    private EquipmentComponent equipmentComponent;
    @Getter
    private StatComponent statComponent;
    @Getter
    private GraphicsComponent graphicsComponent;
    @Getter
    private SpellBookComponent spellBookComponent;

    @Getter
    private HealthBar healthBar;

    public static GameCharacter currentlyChosen = null;
    private ParticleEffect pe = new ParticleEffect();
    @Setter @Getter
    private boolean isCastingSpell = false;
    @Setter @Getter
    private float spellStartX;
    @Setter @Getter
    private float spellStartY;
    @Setter
    private float spellVelocityX;
    @Setter
    private float spellVelocityY;
    @Setter @Getter
    private float spellEndX;
    @Setter @Getter
    private float spellEndY;
    private boolean started = false;

    private Sound fireBallSound;
    private static ShapeRenderer commonShapeRenderer = new ShapeRenderer();



    public GameCharacter(StatComponent statComponent, GraphicsComponent graphicsComponent, EquipmentComponent equipmentComponent, SpellBookComponent spellBookComponent) {

        hp = 100;
        this.setSize(graphicsComponent.getSizeWidth(), graphicsComponent.getSizeHeight());
        this.statComponent = statComponent;
        this.graphicsComponent = graphicsComponent;
        this.equipmentComponent = equipmentComponent;
        this.spellBookComponent = spellBookComponent;

        this.spellStartX = getX() + getWidth();
        this.spellStartY = getY() + (getHeight() / 2) + 210;
        spellVelocityX = spellStartX;
        spellVelocityY = spellStartY;

        fireBallSound = Gdx.audio.newSound(Gdx.files.internal("sounds/fireball_sound.wav"));

        pe.load(Gdx.files.internal("particles/fireball_particle"), Gdx.files.internal(""));


        this.addListener(graphicsComponent.getTouchListener(this));
        this.healthBar = new HealthBar(commonShapeRenderer, getX(), getY() + getHeight() + 15);


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

        healthBar.draw(batch, parentAlpha);


        batch.begin();

        if (isCastingSpell) {
            castSpell(batch);

        }
        graphicsComponent.draw(batch, parentAlpha, getX(), getY(), getWidth(), getHeight());
    }

    public void castSpell(Batch batch) {
        if (!started) {
            started = true;
            pe.getEmitters().first().setPosition(spellStartX, spellStartY);
            pe.start();
            fireBallSound.play(1.0f);
        }
        pe.update(Gdx.graphics.getDeltaTime());
        pe.setPosition(spellVelocityX, spellVelocityY);

        pe.draw(batch);
        if (spellVelocityX >= spellEndX) {
            pe.reset();
            isCastingSpell = false;
            spellVelocityX = spellStartX;
            spellVelocityY = spellStartY;
            started = false;
        }

        spellVelocityX += 15;
        spellVelocityY += (spellEndY - spellStartY) / 50;

    }




}
