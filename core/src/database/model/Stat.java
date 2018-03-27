package database.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Stat {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name="intelligence")
    private int intelligence;

    @Column(name="strength")
    private int strength;

    @Column(name="agility")
    private int agility;
}
