package ui.combat;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Group;
import game.actors.GameCharacter;
import game.actors.Slot;
import game.components.GraphicsComponent;
import game.components.SpellBookComponent;
import game.spells.Spell;
import game.spells.SpellSlot;

import java.util.List;

public class SpellGroup extends Group {

    @SuppressWarnings("FieldCanBeLocal")
    private final int SPELL_SLOT_WIDTH = 50;
    @SuppressWarnings("FieldCanBeLocal")
    private final int SPELL_SLOT_HEIGHT = 50;
    @SuppressWarnings("FieldCanBeLocal")
    private final int SPELL_SLOT_AMOUNT = 9;
    private TextureRegion[][] spells;

    private static Slot[] spellSlots;
    private static SpellGroup reference;
    private static ShapeRenderer sr;

    public SpellGroup(ShapeRenderer sr) {
        spellSlots = new SpellSlot[SPELL_SLOT_AMOUNT];
        this.sr = sr;
        reference = this;
        spells = new GraphicsComponent("spells.png", 15, 11, 736, 539, 15).getTextureRegions();


        createSpellSlots();
    }

    private void createSpellSlots() {
        spellSlots[0] = new SpellSlot(900, 140, SPELL_SLOT_WIDTH, SPELL_SLOT_HEIGHT);
        spellSlots[1] = new SpellSlot(950, 140, SPELL_SLOT_WIDTH, SPELL_SLOT_HEIGHT);
        spellSlots[2] = new SpellSlot(1000, 140, SPELL_SLOT_WIDTH, SPELL_SLOT_HEIGHT);
        spellSlots[3] = new SpellSlot(900, 90, SPELL_SLOT_WIDTH, SPELL_SLOT_HEIGHT);
        spellSlots[4] = new SpellSlot(950, 90, SPELL_SLOT_WIDTH, SPELL_SLOT_HEIGHT);
        spellSlots[5] = new SpellSlot(1000, 90, SPELL_SLOT_WIDTH, SPELL_SLOT_HEIGHT);
        spellSlots[6] = new SpellSlot(900, 40, SPELL_SLOT_WIDTH, SPELL_SLOT_HEIGHT);
        spellSlots[7] = new SpellSlot(950, 40, SPELL_SLOT_WIDTH, SPELL_SLOT_HEIGHT);
        spellSlots[8] = new SpellSlot(1000, 40, SPELL_SLOT_WIDTH, SPELL_SLOT_HEIGHT);

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

    public static void fillSpellSlots() {

        if (GameCharacter.currentlyChosen.getGraphicsComponent().isOpponent()) return;
        if (GameCharacter.currentlyChosen.getSpellBookComponent() == null) return;

        SpellBookComponent sc = GameCharacter.currentlyChosen.getSpellBookComponent();
        List<Spell> items = sc.getSpellSet().getAllSpells();
        int c = 0;
        for (Slot spellSlot : spellSlots) {
            Spell spell = items.get(c);
            if (spellSlot.getActorOnThisSlot() != null && spellSlot.getActorOnThisSlot() != spell ) {
                reference.removeActor(spellSlot.getActorOnThisSlot());
            }

            if (spell != null) {
                spell.setSpellSlot((SpellSlot) spellSlot);
                spell.setSr(sr);
                reference.addActor(spell);
            }
            spellSlot.setActorOnThisSlot(spell);
            c++;
        }
    }
}
