package network.logic;

import game.spells.SpellType;
import network.manager.PlayerCombatInfo;

public class CombatLogicController {
    private boolean playerOneTurn;
    private PlayerCombatInfo playerOneCombatSetup, playerTwoCombatSetup;
    private long turnStartTime;

    public CombatLogicController() {
    }

    public double calculateDamage(SpellType castedSpellType, int dealerSlotId, int targetSlotId) {

        switch (castedSpellType.getModifierStat()){
            case "intelligence":
                if (playerOneTurn) {
                    // TODO: agility damage reduction
                    return castedSpellType.getBaseDamage() * 0.1 * playerOneCombatSetup.getHeroes().get(dealerSlotId).getIntelligence();
                }
                return castedSpellType.getBaseDamage() * 0.1 * playerTwoCombatSetup.getHeroes().get(dealerSlotId).getIntelligence();
            case "agility":
                if (playerOneTurn) {
                    return castedSpellType.getBaseDamage() * 0.1 * playerOneCombatSetup.getHeroes().get(dealerSlotId).getAgility();
                }
                return castedSpellType.getBaseDamage() * 0.1 * playerTwoCombatSetup.getHeroes().get(dealerSlotId).getAgility();
            case "strength":
                if (playerOneTurn) {
                    return castedSpellType.getBaseDamage() * 0.1 * playerOneCombatSetup.getHeroes().get(dealerSlotId).getStrength();
                }
                return castedSpellType.getBaseDamage() * 0.1 * playerTwoCombatSetup.getHeroes().get(dealerSlotId).getStrength();
            default:
                throw new IllegalArgumentException();
        }
    }

    public void setPlayerOneCombatSetup(PlayerCombatInfo playerOneCombatSetup) {
        this.playerOneCombatSetup = playerOneCombatSetup;
    }

    public void setPlayerTwoCombatSetup(PlayerCombatInfo playerTwoCombatSetup) {
        this.playerTwoCombatSetup = playerTwoCombatSetup;
    }
}
