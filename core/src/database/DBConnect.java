package database;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


public class DBConnect {

    public static void main(String[] args) {
//        Configuration conf = new Configuration().configure();
////        conf.configure("hibernate.cfg.xml");
//        conf.addAnnotatedClass(Student.class);
//        SessionFactory factory = conf.buildSessionFactory();

        SessionFactory factory = new Configuration()
                                    .configure("hibernate.cfg.xml")
                                    .addAnnotatedClass(Student.class)
                                    .buildSessionFactory();


        Session session = factory.getCurrentSession();

        try {
            System.out.println("Creating new student object");
            Student tempStudent = new Student(1, "b", 2, 3);

            session.beginTransaction();

            System.out.println("Saving...");
            session.save(tempStudent);

            session.getTransaction().commit();

            System.out.println("done!");

        } finally {
            factory.close();
        }
    }
}

@Entity
@Table(name="test")
@NoArgsConstructor
@AllArgsConstructor
class Student {

    @Id
    @Column(name="first")
    private int first;

    @Column(name="second")
    private String second;

    @Column(name="third")
    private int third;

    @Column(name="fourth")
    private int fourth;
}
