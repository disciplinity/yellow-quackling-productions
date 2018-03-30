package main.java.database.model;

import lombok.*;

@Data
public class HeroSetup {
    private String heroName;
    private int intelligence;
    private int strength;
    private int agility;

    public HeroSetup(String heroName, int intelligence, int strength, int agility) {
        this.heroName = heroName;
        this.intelligence = intelligence;
        this.strength = strength;
        this.agility = agility;
    }
}
