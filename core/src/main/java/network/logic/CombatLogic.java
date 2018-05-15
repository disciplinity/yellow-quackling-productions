package network.logic;

import game.spells.SpellType;
import lombok.Setter;
import network.database.entity.HeroSetupEntity;
import network.manager.PlayerCombatInfo;

// TODO: Separate into Model(which would be common(Server-Client)) and two Controllers(server and client)
public class CombatLogic {
    @Setter
    private boolean playerOneTurn;
    //TODO: Bad repeated encapsulation
    private PlayerCombatInfo playerOneCombatSetup, playerTwoCombatSetup;
    // TODO: Turn time limit. Skip turn if time is out.
    private long turnStartTime;

    public double calculateDamage(SpellType castedSpellType, int dealerSlotId, int targetSlotId) {
        double damage;
        switch (castedSpellType.getModifierStat()){
            case "intelligence":
                if (playerOneTurn) {
                    // TODO: agility damage reduction
                    damage = castedSpellType.getBaseDamage() * 0.1 * playerOneCombatSetup.getHeroes().get(dealerSlotId).getIntelligence();
                } else {
                    damage = castedSpellType.getBaseDamage() * 0.1 * playerTwoCombatSetup.getHeroes().get(dealerSlotId).getIntelligence();
                }
                break;
            case "agility":
                if (playerOneTurn) {
                    damage = castedSpellType.getBaseDamage() * 0.1 * playerOneCombatSetup.getHeroes().get(dealerSlotId).getAgility();
                } else {
                    damage = castedSpellType.getBaseDamage() * 0.1 * playerTwoCombatSetup.getHeroes().get(dealerSlotId).getAgility();
                }
                break;
            case "strength":
                if (playerOneTurn) {
                    damage = castedSpellType.getBaseDamage() * 0.1 * playerOneCombatSetup.getHeroes().get(dealerSlotId).getStrength();
                } else {
                    damage = castedSpellType.getBaseDamage() * 0.1 * playerTwoCombatSetup.getHeroes().get(dealerSlotId).getStrength();
                }
                break;
            default:
                throw new IllegalArgumentException();
        }
        applyDamage(damage, dealerSlotId, targetSlotId);
        return damage;
    }

    private void applyDamage(double damage, int dealerSlotId, int targetSlotId) {
        if (playerOneTurn) {
            HeroSetupEntity hero = playerTwoCombatSetup.getHeroes().get(targetSlotId - 3);
            hero.setHealth(hero.getHealth() - damage);
        } else {
            HeroSetupEntity hero = playerOneCombatSetup.getHeroes().get(targetSlotId - 3);
            hero.setHealth(hero.getHealth() - damage);
        }
    }

    public void endTurn() {
        playerOneTurn = !playerOneTurn;
    }

    public boolean isPlayerOneTurn() {
        return playerOneTurn;
    }

    public void setPlayerOneCombatSetup(PlayerCombatInfo playerOneCombatSetup) {
        this.playerOneCombatSetup = playerOneCombatSetup;
    }

    public void setPlayerTwoCombatSetup(PlayerCombatInfo playerTwoCombatSetup) {
        this.playerTwoCombatSetup = playerTwoCombatSetup;
    }
}
