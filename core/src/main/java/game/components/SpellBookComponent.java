package game.components;

import game.spells.Spell;
import game.spells.SpellSet;
import lombok.Getter;
import lombok.Setter;

public class SpellBookComponent {
    @Getter
    private SpellSet spellSet;

    public static Spell currentSpellChosen;

    public SpellBookComponent(SpellSet spellSet) {
        this.spellSet = spellSet;
    }
}
