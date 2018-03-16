package database;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Data
@Table(name = "CombatSetup")
public class CombatSetup {

    @ManyToOne
    @Column(name = "acc_id")
    private int accId;

    @ManyToOne
    @Column(name = "slot1_hero_id")
    private int firstSlotHeroId;

    @ManyToOne
    @Column(name = "slot2_hero_id")
    private int secondSlotHeroId;

    @ManyToOne
    @Column(name = "slot3_hero_id")
    private int thirdSlotHeroId;
}
