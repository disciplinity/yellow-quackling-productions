package game.actors;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import game.components.EquipmentComponent;
import game.components.GraphicsComponent;
import game.components.SpellBookComponent;
import game.components.StatComponent;
import game.items.Item;
import game.items.ItemInfo;
import game.items.ItemSet;
import game.models.combat.CombatSetup;
import game.spells.Spell;
import game.spells.SpellInfo;
import game.spells.SpellSet;
import game.utils.FontGenerator;
import network.database.entity.HeroSetupEntity;

import java.util.List;

public class CharacterFactory {

    private static TextureRegion[][] items = new GraphicsComponent("items.png", 13, 7, 476, 246, 13).getTextureRegions();
    private static TextureRegion[][] spells = new GraphicsComponent("spells.png", 15, 11, 736, 539, 15).getTextureRegions();
    private static FontGenerator fontGenerator = new FontGenerator("fonts/Raleway-Medium.ttf");

    public static CombatSetup createCombatSetupFrom(List<HeroSetupEntity> heroes) {
        GameCharacter[] chars = new GameCharacter[3];
        for (HeroSetupEntity heroSetupEntity : heroes) {
            System.out.println( heroSetupEntity.getHeroName() + " " + heroSetupEntity.getAgility() + " " + heroSetupEntity.getIntelligence() + " " + heroSetupEntity.getStrength());
        }
        for (int i = 0; i < heroes.size(); i++) {
            HeroSetupEntity hero = heroes.get(i);
            String name = hero.getHeroName();
            StatComponent stats = new StatComponent(hero.getIntelligence(), hero.getStrength(), hero.getAgility());
            chars[i] = createChar(GameCharacterType.valueOf(name), stats);

        }
        return new CombatSetup(chars);
    }

    private static GameCharacter createChar(GameCharacterType type, StatComponent stats) {

        GraphicsComponent graphicsComponent = new GraphicsComponent(type.texturePath, type.columnAmount, type.rowAmount, type.width, type.height, type.endColumn);

        // TODO:
        EquipmentComponent equipmentComponent = createMockEquipmentComponentForMage();
        SpellBookComponent spellBookComponent = createMockSpellBookComponentForMage();
        //

        return new GameCharacter(type.name, stats, graphicsComponent, equipmentComponent, spellBookComponent);
    }


    private static EquipmentComponent createMockEquipmentComponentForMage() {
        ItemInfo helmInfo = new ItemInfo("Helm of the Fallen", "\nDefense: +5\nResist: +2%", items[5][1]);
        ItemInfo mainHandInfo = new ItemInfo("Dagger of Destruction", "\nMagic Attack: 12-17\nCrit chance: +5%", items[0][8]);
        ItemInfo offHandInfo = new ItemInfo("Swampy didgeridoo", "\nStamina: +20\nHP regen: +1%", items[0][9]);
        ItemInfo chestInfo = new ItemInfo("Armor of the Champions", "\nDefense: +10\nResist: +5%", items[2][1]);
        ItemInfo glovesInfo = new ItemInfo("Mystic Gloves", "\nDefense: +3\nResist: +1%", items[1][1]);
        ItemInfo bootsInfo = new ItemInfo("Ak'heia boots", "\nDefense: +5\nResist: +0%\nSpeed: +5%", items[4][1]);
        ItemInfo legsInfo = new ItemInfo("Yirog's sleeves", "\nDefense: +1\nResist: +10%\nImmune to ice damage", items[6][6]);

        Item[] items = {
                new Item(helmInfo),
                new Item(mainHandInfo),
                new Item(offHandInfo),
                null,
                new Item(glovesInfo),
                new Item(bootsInfo),
                null
        };
        for (Item item : items)
            if (item != null)
                item.setFontGenerator(fontGenerator);


        ItemSet itemSet = new ItemSet(items[0], items[1], items[2], items[3], items[4], items[5], items[6]);

        return new EquipmentComponent(itemSet);
    }

    private static EquipmentComponent createMockEquipmentComponentForKnight() {
        ItemInfo helmInfo = new ItemInfo("Steel helm of Percevalt", "\nDefense: +30\nResist: +5%\nImmune to stun effects", items[1][0]);
        ItemInfo mainHandInfo = new ItemInfo("Conqueror", "\nAttack: 30-32\nParry rate: +1%", items[4][8]);
        ItemInfo offHandInfo = new ItemInfo("Molten Hellscreamer", "\nFiery damage: +10%\nStrength: +15", items[3][12]);
        ItemInfo chestInfo = new ItemInfo("Ephemeral cuirass", "\nDefense: +0\nResist: +10%\nAbsorb chance: 2%", items[6][2]);
        ItemInfo glovesInfo = new ItemInfo("Sunholders", "\nDefense: +1\nFire Resist: +15%", items[5][0]);
        ItemInfo bootsInfo = new ItemInfo("Blaze runners", "\nDefense: +1\nFire Resist: +5%\nCan walk on fire", items[3][3]);
        ItemInfo legsInfo = new ItemInfo("Bone-enchanted leggings", "\nCan't be targeted by\nunholy magic", items[1][5]);

        Item[] items = {
                new Item(helmInfo),
                new Item(mainHandInfo),
                new Item(offHandInfo),
                null,
                new Item(glovesInfo),
                new Item(bootsInfo),
                null
        };
        for (Item item : items)
            if (item != null)
                item.setFontGenerator(fontGenerator);

        ItemSet itemSet = new ItemSet(items[0], items[1], items[2], items[3], items[4], items[5], items[6]);

        return new EquipmentComponent(itemSet);
    }

    private static SpellBookComponent createMockSpellBookComponentForKnight() {
        SpellInfo spell1 = new SpellInfo("Rupture", "\nImpales an enemy and makes\n him bleed for 3 turns\n\nDamage per turn: 5-12", spells[1][4]);
        SpellInfo spell2 = new SpellInfo("Blessing from above", "\nImmune to any damage this turn", spells[3][9]);
        SpellInfo spell3 = new SpellInfo("Stone form", "\nArmor increased by 10%", spells[8][13]);
        SpellInfo spell4 = new SpellInfo("Armageddon", "\nDeals AOE damage to\nall enemy characters\n\nDamage: 10-20", spells[3][14]);


        Spell[] spells = {
                new Spell(spell1),
                new Spell(spell2),
                new Spell(spell3),
                new Spell(spell4),
                null,
                null,
                null,
                null,
                null
        };

        for (Spell spell : spells)
            if (spell != null)
                spell.setFontGenerator(fontGenerator);

        SpellSet spellSet = new SpellSet(spells[0], spells[1], spells[2], spells[3], spells[4], spells[5], spells[6], spells[7], spells[8]);

        return new SpellBookComponent(spellSet);
    }

    private static SpellBookComponent createMockSpellBookComponentForMage() {
        SpellInfo spell1 = new SpellInfo("Fireball", "\nGenerate a strong ball of fire\nto be used on an enemy\n\nMagic damage: 20 - 30", spells[3][1]);
        SpellInfo spell2 = new SpellInfo("Poisonous missiles", "\nCast three poisonous missiles\n\nDamage per missile: 5 - 7\nDamage per turn: 3 - 5", spells[10][14]);
        SpellInfo spell3 = new SpellInfo("Dark ritual", "\nSacrifice 10% of health\nfor 10% mana", spells[0][2]);
        SpellInfo spell4 = new SpellInfo("Nightmare grasp", "\nSilence an enemy character\nfor one turn", spells[7][1]);
        SpellInfo spell5 = new SpellInfo("Hell call", "\nMake a demon appear from the\nunderworld for this turn\n\nMagic damage: 30 - 40", spells[3][0]);
        SpellInfo spell6 = new SpellInfo("Nuke", "\nDeal fire damage to \nall enemy characters\n\nMagic damage: 15 - 25", spells[1][14]);
        SpellInfo spell7 = new SpellInfo("Elemental form", "\nTurn into fire element for\nthree turns\n\nImmune to damage this turn\nDamage multiplier 1.25", spells[10][11]);


        Spell[] spells = {
                new Spell(spell1),
                new Spell(spell2),
                new Spell(spell3),
                new Spell(spell4),
                new Spell(spell5),
                new Spell(spell6),
                new Spell(spell7),
                null,
                null
        };

        for (Spell spell : spells)
            if (spell != null)
                spell.setFontGenerator(fontGenerator);

        SpellSet spellSet = new SpellSet(spells[0], spells[1], spells[2], spells[3], spells[4], spells[5], spells[6], spells[7], spells[8]);

        return new SpellBookComponent(spellSet);
    }

}
