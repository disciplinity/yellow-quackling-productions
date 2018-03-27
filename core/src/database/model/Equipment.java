package database.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "Equipment")
public class Equipment {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "helm")
    private String helm;

    @Column(name = "chest")
    private String chest;

    @Column(name = "legs")
    private String legs;

    @Column(name = "gloves")
    private String gloves;

    @Column(name = "boots")
    private String boots;

    @Column(name = "main_hand")
    private String mainHand;

    @Column(name = "off_hand")
    private String offHand;
}
