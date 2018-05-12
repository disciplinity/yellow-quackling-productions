package game.spells;

import lombok.AllArgsConstructor;

import java.util.ArrayList;
import java.util.List;

public class SpellSet {

    private Spell slot1;
    private Spell slot2;
    private Spell slot3;
    private Spell slot4;
    private Spell slot5;
    private Spell slot6;
    private Spell slot7;
    private Spell slot8;
    private Spell slot9;

    private List<Spell> allSpells;

    public SpellSet(Spell slot1, Spell slot2, Spell slot3, Spell slot4, Spell slot5, Spell slot6, Spell slot7, Spell slot8, Spell slot9) {
        this.slot1 = slot1;
        this.slot2 = slot2;
        this.slot3 = slot3;
        this.slot4 = slot4;
        this.slot5 = slot5;
        this.slot6 = slot6;
        this.slot7 = slot7;
        this.slot8 = slot8;
        this.slot9 = slot9;


        allSpells = new ArrayList<>();
        allSpells.add(slot1);
        allSpells.add(slot2);
        allSpells.add(slot3);
        allSpells.add(slot4);
        allSpells.add(slot5);
        allSpells.add(slot6);
        allSpells.add(slot7);
        allSpells.add(slot8);
        allSpells.add(slot9);


    }

    public List<Spell> getAllSpells() {
        return allSpells;
    }

}
