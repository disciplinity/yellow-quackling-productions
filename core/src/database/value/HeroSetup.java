package database.value;

import lombok.*;

@Data
public class HeroSetup {
    @Setter
    @Getter
    private String heroName;

    @Setter
    @Getter
    private int intelligence;

    @Setter
    @Getter
    private int strength;

    @Setter
    @Getter
    private int agility;

    public HeroSetup(String heroName, int intelligence, int strength, int agility) {
        this.heroName = heroName;
        this.intelligence = intelligence;
        this.strength = strength;
        this.agility = agility;
    }
}
