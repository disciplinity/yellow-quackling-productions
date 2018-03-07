package actors;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import components.EquipmentComponent;
import components.GraphicsComponent;
import components.SpellBookComponent;
import components.StatComponent;
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
