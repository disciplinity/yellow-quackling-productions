package database.model;

import lombok.Data;

import javax.persistence.*;

/**
 * This Entity is just Annotation holder. It is never used as is. I suppose there is no other solution, so it exists.
 */
@NamedNativeQueries({
        @NamedNativeQuery(name = "HeroCombatQuery", query = "SELECT HC.name, S.intelligence, S.strength, S.agility " +
                "FROM Stat S, CombatSetup CS, Account A, HeroClassifier HC, Hero H " +
                "WHERE A.ID = CS.acc_id AND " +
                "HC.id = H.hero_id AND " +
                "S.id = H.stat_id AND " +
                "(H.id = CS.slot1_hero_id OR " +
                "H.id = CS.slot2_hero_id OR " +
                "H.id = CS.slot3_hero_id) AND " +
                "A.ID=:playerId ;", resultSetMapping = "HeroSetupMapping")
})
@SqlResultSetMappings({
        @SqlResultSetMapping(
                name = "HeroSetupMapping",
                classes = @ConstructorResult(
                        targetClass = HeroSetup.class,
                        columns = {
                                @ColumnResult(name = "name"),
                                @ColumnResult(name = "intelligence"),
                                @ColumnResult(name = "strength"),
                                @ColumnResult(name = "agility")}))
})
@Data
@Entity
public class Account {

    @Id
    @Column(name = "id", updatable = false, nullable = false)
    private int id;
}
