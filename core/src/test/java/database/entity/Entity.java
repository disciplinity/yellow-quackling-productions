//package database.entity;
//
//import lombok.Data;
//
//import javax.persistence.*;
//
//@NamedNativeQueries({
//        @NamedNativeQuery(name = "SuccessfulTestQuery", query = "SELECT a.username, cs.acc_id FROM Account a, CombatSetup cs WHERE a.id=cs.acc_id", resultSetMapping = "SuccessfulTestMapping")
//})
//@SqlResultSetMappings({
//        @SqlResultSetMapping(name = "SuccessfulTestMapping",
//                classes = {
//                        @ConstructorResult(targetClass = QueryResultConstructorTest.class,
//                                columns = {@ColumnResult(name = "username"), @ColumnResult(name = "acc_id")}
//                        )}
//        )
//})
//@Data
//@javax.persistence.Entity
//public class Entity {
//    @Id
//    @Column(name = "id", updatable = false, nullable = false)
//    private int id;
//}
