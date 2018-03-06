package battle;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Group;
import lombok.Getter;
import models.CombatSetup;

import java.util.List;

/**
 * Class that holds:
 *  - background image
 *  - character slots with appropriate characters assigned to them
 */
public class BattleStageGroup extends Group {

    // TODO: method that will fill character slots (pass PlayerCombatHeroSetup)
    @Getter
    private CharacterSlot[] characterSlots;
    private List<Actor> actorGroup;

    private List<Actor> opponentGroupAkaSetupPleaseChangeMyNameAndObjectModelWhereIamLocated;

    private String imageName;

    public BattleStageGroup(String imageName, CombatSetup combatSetup, CombatSetup opponentCombatSetup) {
        characterSlots = new CharacterSlot[6];
        this.actorGroup = combatSetup.getCombatSetup();
        this.opponentGroupAkaSetupPleaseChangeMyNameAndObjectModelWhereIamLocated = opponentCombatSetup.getCombatSetup();
        this.imageName = imageName;

        setCharacterSlotPositions();
        spawnCharacters();
    }

    /**
     * Depending on the background image, set the positions where characters will stand.
     */
    private void setCharacterSlotPositions() {
        switch(imageName) {
            case "fairy-forest.jpg":
                // Array size should be managed at once. Here we are Recreating it every line. I think...
                characterSlots[0] = new CharacterSlot(0,0);
                characterSlots[1] = new CharacterSlot(100, 100);
                characterSlots[2] = new CharacterSlot(200, 200);
                characterSlots[3] = new CharacterSlot(800,0);
                characterSlots[4] = new CharacterSlot(700, 100);
                characterSlots[5] = new CharacterSlot(600, 200);
        }
    }

    private void spawnCharacters() {
        // TODO: here should be only one method putActor be used. Set actor position in that method. (delegate texture positioning from here to another method.)
        for (int i = 0; i < characterSlots.length; i++) {
            Actor actor;
            if (i < 3) {
                actor = actorGroup.get(i);
            } else {
                actor = opponentGroupAkaSetupPleaseChangeMyNameAndObjectModelWhereIamLocated.get(i - 3);
            }
            actor.setPosition(characterSlots[i].getX(), characterSlots[i].getY());
            actor.setBounds(actor.getX(), actor.getY(), actor.getWidth(), actor.getHeight());
            characterSlots[i].putActor(actor);
            this.addActor(actor);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        for (CharacterSlot cs : characterSlots) {
            cs.getActor().draw(batch, parentAlpha);
        }
    }

}
