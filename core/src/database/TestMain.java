package database;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.jpa.boot.internal.EntityManagerFactoryBuilderImpl;
import org.hibernate.query.Query;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.util.List;

public class TestMain {

    public static void main(String[] args) {

        SessionFactory sessionFactory = HibernateUtils.getSessionFactory();
        Session session = sessionFactory.getCurrentSession();
        Transaction transaction = session.beginTransaction();

        try {

//            String sql = "SELECT hc.name, new " + StatComponent.class.getName()
//                    + "(sc.id, sc.intellect, sc.strength, sc.agility)"
//                    + ", new " + EquipmentComponent.class.getName() +
//                    "(ec.id, ec.helm, ec.chest, ec.legs, ec.gloves, ec.boots, ec.mainHand, ec.offHand)"
//                    + " FROM HeroClassifier hc, Stat sc, Equipment ec;";

            String sql = "SELECT hc.name, sc, ec FROM HeroClassifier hc, StatComponent sc, EquipmentComponent ec";
//            List<Object[]> result = session.createQuery(sql).getResultList();
            List<Object[]> result = session.createQuery(sql).list();

            for (Object[] obj : result) {

            }

            transaction.commit();
            session.close();
//            transaction.close();


        } catch (Exception e) {
            e.printStackTrace();
//            em.getTransaction().rollback();
            transaction.rollback();
            session.close();

        }
    }
}
