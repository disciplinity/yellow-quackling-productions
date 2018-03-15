package database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl;
import org.hibernate.query.Query;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class TestMain {

    public static void main(String[] args) {
        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        EntityManager em = session.getEntityManagerFactory().createEntityManager();

        try {
            session.getTransaction().begin();

            String sql = "Select a, b from " + Account.class.getName() + " a INNER JOIN " + StatComponent.class.getName() + " b";

            List<Object[]> result = em.createQuery(sql).getResultList();

            for (Object obj : result) {
                if (obj instanceof Account) {
                    System.out.println("Account");
                    System.out.println(((Account) obj).getUsername());
                }
                else if (obj instanceof StatComponent) {
                    System.out.println("StatComponent");
                    System.out.println(((StatComponent) obj).getAgility());
                }
            }

            em.getTransaction().commit();
            em.close();


        } catch (Exception e) {
            e.printStackTrace();
            em.getTransaction().rollback();
        }
    }
}
