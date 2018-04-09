package database;

import main.java.database.model.HeroSetup;
import game.components.StatComponent;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

public class DBConnector {

    // TODO: For the "no red line in code' purposes
    public static StatComponent fetchStats(int playerId) {
        // TODO: MAKE THIS CLASS NOT STATIC!!! AND YOU KNOW WHAT TO DO HERE!
        return new StatComponent(10, 10, 10);
    }

    // TODO: Working crap. Has to be refactored a bit (return type)
    private static void fetchCombatSetup(SessionFactory factory) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        List<HeroSetup> results = session.getNamedNativeQuery("HeroCombatQuery").setParameter("playerId", 2).list();

        results.forEach((record) -> {
            System.out.println(record.getHeroName());
        });

        session.getTransaction().commit();
        session.close();
    }

    // TODO: No main, refactor
    public static void main(String[] args) {

        try (SessionFactory factory = new Configuration()
                .configure()
                .buildSessionFactory()) {

            fetchCombatSetup(factory);
        }
    }
}
