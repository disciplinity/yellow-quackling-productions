package game.actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import game.components.EquipmentComponent;
import game.components.SpellBookComponent;
import game.components.GraphicsComponent;
import game.components.StatComponent;
import lombok.AllArgsConstructor;

@AllArgsConstructor
public class Character extends Actor {

    private double hp;
    private double force;
    private SpellBookComponent spellBookComponent;
    private EquipmentComponent equipmentComponent;
    private StatComponent statComponent;
    private GraphicsComponent graphicsComponent;


    @Override
    public void draw(Batch batch, float parentAlpha) {

    }
}
