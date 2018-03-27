package game.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import database.model.Equipment;
import game.components.StatComponent;
import game.components.GraphicsComponent;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
public class GameCharacter extends Actor {

    private double hp;
    private double force;
//    private SpellBookComponent spellBookComponent;
    private Equipment equipmentComponent;
    @Getter
    private StatComponent statComponent;
    @Getter
    private GraphicsComponent graphicsComponent;

    public static GameCharacter currentlyChosen = null;

    public GameCharacter(StatComponent statComponent, GraphicsComponent graphicsComponent, Equipment equipmentComponent) {
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
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        graphicsComponent.draw(batch, parentAlpha, getX(), getY(), getWidth(), getHeight());
    }
}
