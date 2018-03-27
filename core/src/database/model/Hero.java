package database.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Hero {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private int id;

    @Column(name="heroId")
    private int heroId;

    @Column(name="stat_id")
    private int statId;

    @Column(name="equipment_id")
    private int equipId;
}
