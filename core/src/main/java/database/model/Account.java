package database.model;

import database.value.HeroSetup;
import lombok.Data;

import javax.persistence.*;
@NamedNativeQueries({
        @NamedNativeQuery(name="HeroCombatQuery", query="SELECT HC.name, S.intelligence, S.strength, S.agility " +
                "FROM Stat S, CombatSetup CS, Account A, HeroClassifier HC, Hero H " +
                "WHERE A.ID = CS.acc_id AND " +
                "HC.id = H.hero_id AND " +
                "S.id = H.stat_id AND " +
                "(H.id = CS.slot1_hero_id OR " +
                "H.id = CS.slot2_hero_id OR " +
                "H.id = CS.slot3_hero_id) AND " +
                "A.ID=:playerId ;", resultSetMapping="HeroSetupMapping")
})
@SqlResultSetMappings({

        @SqlResultSetMapping(
                name = "AccountCombatSetupMapping",
                entities = {
                        @EntityResult(
                                entityClass = Account.class,
                                fields = {
                                        @FieldResult(name = "id", column = "id"),
                                        @FieldResult(name = "username", column = "username"),
                                        @FieldResult(name = "pwd", column = "pwd")}),
                        @EntityResult(
                                        entityClass = CombatSetup.class,
                                fields = {
                                        @FieldResult(name = "id", column = "id"),
                                        @FieldResult(name = "acc_id", column = "acc_id"),
                                        @FieldResult(name = "slot1_hero_id", column = "slot1_hero_id"),
                                        @FieldResult(name = "slot2_hero_id", column = "slot2_hero_id"),
                                        @FieldResult(name = "slot3_hero_id", column = "slot3_hero_id")})}),
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

    @Column(name="username")
    private String username;

    @Column(name="pwd")
    private String pwd;
}
