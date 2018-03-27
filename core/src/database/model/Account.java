package database.model;

import database.value.AccountHeroSetup;
import database.value.HeroSetup;
import lombok.Data;

import javax.persistence.*;
@NamedNativeQueries({
        @NamedNativeQuery(name="HeroCombatQuery", query="SELECT HeroClassifier.name, Stat.intelligence," +
                " Stat.strength, Stat.agility\n" +
                "FROM Stat, CombatSetup, Account, HeroClassifier, Hero \n" +
                "WHERE Account.ID = CombatSetup.acc_id AND \n" +
                "HeroClassifier.id = Hero.hero_id AND \n" +
                "Stat.id = Hero.stat_id AND \n" +
                "(Hero.id = CombatSetup.slot1_hero_id OR \n" +
                "Hero.id = CombatSetup.slot2_hero_id OR \n" +
                "Hero.id = CombatSetup.slot3_hero_id) AND \n" +
                "Account.ID = 1;", resultSetMapping="HeroSetupMapping"),
        @NamedNativeQuery(name="SuccessfulTestQuery", query="SELECT a.username, cs.acc_id FROM Account a, CombatSetup cs WHERE a.id=cs.acc_id", resultSetMapping="SuccessfulTestMapping")
})
@SqlResultSetMappings({
        @SqlResultSetMapping(name="SuccessfulTestMapping",
                classes = {
                        @ConstructorResult(targetClass = AccountHeroSetup.class,
                                columns = {@ColumnResult(name="username"), @ColumnResult(name="acc_id")}
                        )}
        ),
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
