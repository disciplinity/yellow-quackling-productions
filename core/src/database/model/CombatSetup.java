package database.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
public class CombatSetup {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private int id;

    @Column(name="acc_id")
    private int accId;

    @Column(name="slot1_hero_id")
    private int slot1HeroId;

    @Column(name="slot2_hero_id")
    private int slot2HeroId;

    @Column(name="slot3_hero_id")
    private int slot3HeroId;
}
