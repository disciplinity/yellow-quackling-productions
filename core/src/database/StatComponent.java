package database;

import lombok.AllArgsConstructor;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@Entity
@Table(name = "Stat")
public class StatComponent {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "intellect")
    private int intellect;

    @Column(name = "strength")
    private int strength;

    @Column(name = "agility")
    private int agility;

}