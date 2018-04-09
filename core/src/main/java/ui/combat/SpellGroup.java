package ui.combat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Group;
import game.actors.Slot;
import game.spells.SpellSlot;

public class SpellGroup extends Group {

    @SuppressWarnings("FieldCanBeLocal")
    private final int SPELL_SLOT_WIDTH = 50;
    @SuppressWarnings("FieldCanBeLocal")
    private final int SPELL_SLOT_HEIGHT = 50;
    @SuppressWarnings("FieldCanBeLocal")
    private final int SPELL_SLOT_AMOUNT = 9;

    private Slot[] spellSlots;
    private ShapeRenderer sr;

    public SpellGroup(ShapeRenderer sr) {
        spellSlots = new SpellSlot[SPELL_SLOT_AMOUNT];
        this.sr = sr;
//        Gdx.gl.glLineWidth(2);

        createSpellSlots();
    }

    private void createSpellSlots() {
        spellSlots[0] = new SpellSlot(900, 140, SPELL_SLOT_WIDTH, SPELL_SLOT_HEIGHT);
        spellSlots[1] = new SpellSlot(952, 140, SPELL_SLOT_WIDTH, SPELL_SLOT_HEIGHT);
        spellSlots[2] = new SpellSlot(1004, 140, SPELL_SLOT_WIDTH, SPELL_SLOT_HEIGHT);
        spellSlots[3] = new SpellSlot(900, 88, SPELL_SLOT_WIDTH, SPELL_SLOT_HEIGHT);
        spellSlots[4] = new SpellSlot(952, 88, SPELL_SLOT_WIDTH, SPELL_SLOT_HEIGHT);
        spellSlots[5] = new SpellSlot(1004, 88, SPELL_SLOT_WIDTH, SPELL_SLOT_HEIGHT);
        spellSlots[6] = new SpellSlot(900, 36, SPELL_SLOT_WIDTH, SPELL_SLOT_HEIGHT);
        spellSlots[7] = new SpellSlot(952, 36, SPELL_SLOT_WIDTH, SPELL_SLOT_HEIGHT);
        spellSlots[8] = new SpellSlot(1004, 36, SPELL_SLOT_WIDTH, SPELL_SLOT_HEIGHT);

        for (Slot spellSlot : spellSlots) {
            spellSlot.setSr(sr); // we are reusing our current shaperenderer, because it's an expensive object
//            spellSlot.setBounds(getX(), getY(), getWidth(), getHeight());
            this.addActor(spellSlot);
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.end();
        for (Slot spellSlot : spellSlots) {
            spellSlot.draw(batch, parentAlpha);
        }
        batch.begin();
    }
}
