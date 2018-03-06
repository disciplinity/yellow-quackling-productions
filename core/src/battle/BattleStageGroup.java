package battle;

import actors.IceMage;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.InputListener;
import components.StatComponent;
import lombok.Getter;

/**
 * Class that holds:
 *  - background image
 *  - character slots with appropriate characters assigned to them
 */
public class BattleStageGroup extends Group {

    @Getter
    private CharacterSlot[] characterSlots;

    private String imageName;

    public BattleStageGroup(String imageName) {
        characterSlots = new CharacterSlot[3];
        this.imageName = imageName;

        setCharacterSlotPositions();
        spawnCharacters();

//        setOrigin(0, 230);

    }

    /**
     * Depending on the background image, set the positions where characters will stand.
     */
    private void setCharacterSlotPositions() {
        switch(imageName) {
            case "fairy-forest.jpg":
                characterSlots[0] = new CharacterSlot(0,0);
                characterSlots[1] = new CharacterSlot(100, 100);
                characterSlots[2] = new CharacterSlot(200, 200);


        }
    }

    private void spawnCharacters() {
        for (int i = 0; i < characterSlots.length; i++) {
            Actor actor = new IceMage(new StatComponent(105, 105, 105, 105, 100));
            actor.setPosition(characterSlots[i].getX(), characterSlots[i].getY());
            actor.setBounds(actor.getX(), actor.getY(), actor.getWidth(), actor.getHeight());
            characterSlots[i].putActor(actor);
            this.addActor(actor);

        }
//        CharacterFactory.characterSpawner()
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
//        batch.draw(backgroundImage, getX(), getY(), getWidth(), getHeight());
        for (CharacterSlot cs : characterSlots) {
           cs.getActor().draw(batch, parentAlpha);
        }
    }

}
