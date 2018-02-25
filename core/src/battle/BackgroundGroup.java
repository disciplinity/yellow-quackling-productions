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
public class BackgroundGroup extends Group {

    @Getter
    private CharacterSlot[] characterSlots;

    @Getter
    private Texture backgroundImage;
    private String imageName;

    public BackgroundGroup(String imageName) {
        characterSlots = new CharacterSlot[2];
        backgroundImage = new Texture(imageName);
        this.imageName = imageName;

        setX(0);
        setY(230);
        setWidth(1280);
        setHeight(570);

        setCharacterSlotPositions();
        spawnCharacters();

    }

    /**
     * Depending on the background image, set the positions where characters will stand.
     */
    private void setCharacterSlotPositions() {
        switch(imageName) {
            case "fairy-forest.jpg":
                characterSlots[0] = new CharacterSlot(1,1);
                characterSlots[1] = new CharacterSlot(25, 100);
//                characterSlots[2] = new CharacterSlot(3, 3);
//                characterSlots[3] = new CharacterSlot(4, 4);
//                characterSlots[4] = new CharacterSlot(5, 5);
//                characterSlots[5] = new CharacterSlot(6, 6);
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
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(backgroundImage, getX(), getY(), getWidth(), getHeight());
        for (CharacterSlot cs : characterSlots) {
            cs.getActor().draw(batch, parentAlpha);
        }
    }

}
