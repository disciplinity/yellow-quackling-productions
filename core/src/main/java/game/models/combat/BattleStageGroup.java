package game.models.combat;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Group;
import game.actors.GameCharacter;
import lombok.Getter;

import java.util.List;


// TODO: ПЕРЕОСМЫСЛИТЬ РОЛЬ ЭТОГО КЛАССА, МНЕ КАЖЕТСЯ, ОН ДОЛЖЕН УСТАНАВЛИВАТЬ ТОЛЬКО(!) КООРДИНАТЫ СЛОТОВ НА КАРТЕ,
// TODO: ОТМЕЧАТЬ КАКИЕ СЛОТЫ БУДУТ "ПЕРЕВЕРНУТЫ", ИБО ВРАГИ И СОДЕРЖАТЬ ТЕКСТУРУ ФОНА.
// TODO: ТО ЕСТЬ БЫТЬ ЕЩЕ ОДНОЙ МОДЕЛЬЮ В НАШЕЙ СЛОЖНОЙ МОДЕЛЬНОЙ АРХИТЕКТУРЕ.
// TODO: С ДРУГОЙ СТОРОНЫ, ЭТОТ "МОДУЛЬ" МОЖЕТ БЫТЬ НЕ ТОЛЬКО МОДЕЛЬЮ, А И КОНТРОЛЛЕРОМ, ИБО ВЫЗЫВАЕТ DRAW У ВСЕХ АКТЕРОВ
// TODO: ЧТО КАК БЫ НЕ ХОРОШО, НО РАЗ УЖ ЭТОТ МЕТОД ВСТРОЕН В ЭТОТ КЛАССС И ОН ВСЕГО ОДИН И ОЧЕНЬ ПРОСТОЙ, ТО МОЖЕТ БЫТЬ НЕ ВСЕ ТАК ПЛОХО
/**
 * Class that holds:
 *  - background image
 *  - character slots with appropriate characters assigned to them
 */
public class BattleStageGroup extends Group {

    // TODO: method that will fill character slots (pass PlayerCombatHeroSetup)
    @Getter
    private CharacterSlot[] characterSlots;
    private List<GameCharacter> actorGroup;

    private List<GameCharacter> opponentGroupAkaSetupPleaseChangeMyNameAndObjectModelWhereIamLocated;

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
                characterSlots[2] = new CharacterSlot(250, 200);
                characterSlots[3] = new CharacterSlot(1000,20);
                characterSlots[4] = new CharacterSlot(900, 100);
                characterSlots[5] = new CharacterSlot(800, 200);
        }
    }

    private void spawnCharacters() {
        // TODO: here should be only one method putActor be used. Set actor position in that method. (delegate texture positioning from here to another method.)
        for (int i = 0; i < characterSlots.length; i++) {
            GameCharacter actor;
            if (i < 3) {
                actor = actorGroup.get(i);
            } else {
                actor = opponentGroupAkaSetupPleaseChangeMyNameAndObjectModelWhereIamLocated.get(i - 3);
                // TODO: interface for our game.actors
                actor.getGraphicsComponent().setOpponent();
            }
            // TODO: Этот актёр совершает совсем уж какие-то грешные для модели вещи :)
            // (мб перенести и эту часть кода в графический компонент и вызывать её через метод, обращаясь к этому компоненту,
            // через модель персонажа, содержащую этот компонент???)
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
