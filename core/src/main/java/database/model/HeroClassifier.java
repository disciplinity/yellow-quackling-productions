package database.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class HeroClassifier {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name="name")
    private String heroName;
}
