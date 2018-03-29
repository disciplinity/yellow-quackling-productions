package database;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "hero")
public class Hero {

    @Id
    @Column(name = "id")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hero_id")
    private Integer heroId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stat_id")
    private Integer statId;

    @ManyToOne(targetEntity = EquipmentComponent.class)
    @JoinColumn(name = "equipment_id")
    private Integer equipmentId;


}
