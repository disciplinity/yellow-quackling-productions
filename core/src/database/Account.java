package database;

import lombok.Data;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


/**
 * Fuck knows why the intellij shows errors in this file. It compiles and works correctly.
 */
@Entity
@Data
@Table(name="Account")
public class Account {

    @Id
    @Column(name = "id")
    private int id;

    @Column(name = "username")
    private String username;

    @Column(name = "pwd")
    private String password;
}
