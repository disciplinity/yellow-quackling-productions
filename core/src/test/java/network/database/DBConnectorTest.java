package network.database;

import network.database.entity.QueryResultConstructorTest;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class DBConnectorTest {

    private static SessionFactory factory;

    @Before
    public void initializeSessionAndFactory() {
        try {
            factory = new Configuration()
                    .configure("test_hibernate.cfg.xml")
                    .buildSessionFactory();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
    }

    @After
    public void closeConnection() {
        factory.close();
    }

    @Test
    public void testFetchWithNativeQueryAccountIdAndName() {
        Session session = null;
        try {
            session = factory.getCurrentSession();
        } catch (HibernateException e) {
            e.printStackTrace();
        }
        session.beginTransaction();
        List<QueryResultConstructorTest> results = session.getNamedNativeQuery("SuccessfulTestQuery").getResultList();

        results.forEach(System.out::println);

        session.getTransaction().commit();
        session.close();
    }


    //TODO: Check if more than one entity is created with appropriate quary

}
