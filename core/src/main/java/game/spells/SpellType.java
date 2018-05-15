package game.spells;

public enum SpellType {
    FIREBALL(0, 12, "intelligence"),
    LIGHTNING(1, 10, "agility"),
    STONE_PUNCH(1, 10, "strength");

    private final int id;
    private final int baseDamage;
    private final String modifierStat;

    SpellType(int id, int baseDamage, String modifierStat) {
        this.id = id;
        this.baseDamage = baseDamage;
        this.modifierStat = modifierStat;
    }

    public int getId() {
        return id;
    }

    public int getBaseDamage() {
        return baseDamage;
    }

    public String getModifierStat() {
        return modifierStat;
    }

}
