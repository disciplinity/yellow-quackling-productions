package network.database;

import network.database.entity.AccountEntity;
import network.database.entity.HeroSetupEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import javax.persistence.NoResultException;
import java.util.List;

public class DBConnector {


    public static List<HeroSetupEntity> fetchCombatSetup(int playerId, SessionFactory factory) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        List<HeroSetupEntity> results = (List<HeroSetupEntity>) session.getNamedNativeQuery("HeroCombatQuery").setParameter("playerId", playerId).list();

        session.getTransaction().commit();
        session.close();
        return results;
    }

    public static AccountEntity fetchUserCredentials(String username, SessionFactory factory) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        AccountEntity account = null;
        try {
            account = session.createQuery("SELECT a FROM AccountEntity a WHERE :username =a.username", AccountEntity.class).setParameter("username", username).getSingleResult();
        } catch(NoResultException e) {
            e.printStackTrace();
        }

        session.getTransaction().commit();
        session.close();
        return account;
    }

    // TODO: No main, refactor
    public static void main(String[] args) {

        try (SessionFactory factory = HibernateSessionFactory.getSessionFactory()) {

            List<HeroSetupEntity> results = fetchCombatSetup(2 , factory);

            results.forEach((record) -> {
                System.out.println(record.getHeroName());
            });
            AccountEntity acc = fetchUserCredentials("Jaro1", factory);
            System.out.println(acc.getPwd());
        }
    }
}
