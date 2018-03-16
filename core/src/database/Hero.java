package database;


import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "hero")
public class Hero {

    @Id
    @Column(name = "id")
    private int id;

    @ManyToOne
    @Column(name = "hero_id")
    private int heroId;

    @ManyToOne
    @Column(name = "stat_id")
    private int statId;

    @ManyToOne
    @Column(name = "equipment_id")
    private int equipmentId;


}
