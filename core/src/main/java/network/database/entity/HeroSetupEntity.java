package network.database.entity;

import lombok.*;

@Data
public class HeroSetupEntity {
    private String heroName;
    private int intelligence;
    private int strength;
    private int agility;
    private double health;

    public HeroSetupEntity() {}

    public HeroSetupEntity(String heroName, int intelligence, int strength, int agility) {
        this.heroName = heroName;
        this.intelligence = intelligence;
        this.strength = strength;
        this.agility = agility;
        //TODO: vitality stat or what?
        this.health = strength;
    }
}
