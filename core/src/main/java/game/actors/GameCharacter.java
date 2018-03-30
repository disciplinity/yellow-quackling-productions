package main.java.game.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import lombok.ToString;
import main.java.game.components.EquipmentComponent;
import main.java.game.components.StatComponent;
import main.java.game.components.GraphicsComponent;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class GameCharacter extends Actor {

    private double hp;
    private double force;
    //    private SpellBookComponent spellBookComponent;
    private EquipmentComponent equipmentComponent;
    @Getter
    private StatComponent statComponent;
    @Getter
    private GraphicsComponent graphicsComponent;

    private static int sid = 0;
    private int id = 0;

    public static GameCharacter currentlyChosen = null;

    public GameCharacter(StatComponent statComponent, GraphicsComponent graphicsComponent, EquipmentComponent equipmentComponent) {
        this.statComponent = statComponent;
        this.graphicsComponent = graphicsComponent;
        this.equipmentComponent = equipmentComponent;
    }

    public GameCharacter(StatComponent statComponent, GraphicsComponent graphicsComponent) {
        this.setSize(graphicsComponent.getSizeWidth(), graphicsComponent.getSizeHeight());
        this.statComponent = statComponent;
        this.graphicsComponent = graphicsComponent;

        // TODO: Ну чисто теоретически. Эта Модель хранит в себе листэнер. Всё законно. Его назначает контроллер, и используется он во вью. Sounds kind of legal.
        this.addListener(graphicsComponent.getTouchListener(this));

        id = ++sid;

    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        graphicsComponent.draw(batch, parentAlpha, getX(), getY(), getWidth(), getHeight());
    }

    @Override
    public String toString() {
        return String.valueOf(id);
    }

}
