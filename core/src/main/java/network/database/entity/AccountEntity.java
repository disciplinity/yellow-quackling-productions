package network.database.entity;

import lombok.Data;

import javax.persistence.*;
import javax.persistence.Entity;
import java.util.Objects;

@Data
@Entity
@Table(name = "Account", schema = "sql11225282", catalog = "")
public class AccountEntity {

    @Id
    @Column(name = "id", nullable = false)
    private int id;
    @Basic
    @Column(name = "username", nullable = false, length = 20)
    private String username;
    @Basic
    @Column(name = "pwd", nullable = false, length = 20)
    private String pwd;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountEntity that = (AccountEntity) o;
        return id == that.id &&
                Objects.equals(username, that.username) &&
                Objects.equals(pwd, that.pwd);
    }

    @Override
    public int hashCode() {

        return Objects.hash(id, username, pwd);
    }
}
