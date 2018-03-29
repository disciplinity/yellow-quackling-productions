package database;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "CombatSetup")
public class CombatSetup {

    @Id
    @Column(name = "id")
        private int id;

    @ManyToOne
    @JoinColumn(name = "acc_id")
    private int accId;

    @ManyToOne
    @JoinColumn(name = "slot1_hero_id")
    private int firstSlotHeroId;

    @ManyToOne
    @JoinColumn(name = "slot2_hero_id")
    private int secondSlotHeroId;

    @ManyToOne
    @JoinColumn(name = "slot3_hero_id")
    private int thirdSlotHeroId;
}
