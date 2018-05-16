package game.screens.preferences;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import game.actors.GameCharacter;
import game.models.combat.CharacterSlot;
import game.session.GameSession;
import lombok.Getter;

import java.util.List;

public class CharacterSetup extends Group {
    @Getter
    private CharacterSlot[] characterSlots;
    private List<GameCharacter> characters;

    public CharacterSetup() {
        characterSlots = new CharacterSlot[3];
        this.characters = GameSession.getInstance().getPlayerCombatSetup().getCombatSetup();

        setCharacterPositionOnPlatform();
        spawnCharacters();
    }

    private void setCharacterPositionOnPlatform() {
        characterSlots[0] = new CharacterSlot(140, -80); // new CharacterSlot(-200, -70);
        characterSlots[1] = new CharacterSlot(140, 150); // new CharacterSlot(-200, 150);
        characterSlots[2] = new CharacterSlot(200, 400); // new CharacterSlot(-200, 370);
    }

    private void spawnCharacters() {
        for (int i = 0; i < characterSlots.length; i++) {
            GameCharacter character;
            character = characters.get(i);
            character.getGraphicsComponent().setPosition(character, characterSlots[i].getX(), characterSlots[i].getY());
            character.getGraphicsComponent().setBounds(character);
            characterSlots[i].putActor(character);
            this.addActor(character);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        for (CharacterSlot cs : characterSlots) {
            cs.getActor().draw(batch, parentAlpha);
        }
    }
}
