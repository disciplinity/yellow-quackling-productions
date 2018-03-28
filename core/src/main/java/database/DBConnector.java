package database;

import database.value.AccountHeroSetup;
import database.value.HeroSetup;
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

    // TODO: For the test purposes. Move to the test class
    private static void fetchHeroAndCombatSetupSuccessfulTest(SessionFactory factory) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        List<AccountHeroSetup> results = session.getNamedNativeQuery("SuccessfulTestQuery").getResultList();

        results.stream().forEach((record) -> {
            System.out.println(record);
        });

        session.getTransaction().commit();
        session.close();
    }

    // TODO: Working crap. Has to be refactored a bit (return type)
    private static void fetchCombatSetup(SessionFactory factory) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        List<HeroSetup> results = session.getNamedNativeQuery("HeroCombatQuery").setParameter("playerId",1).list();

        System.out.println("HELLO");
        results.stream().forEach((record) -> {
            System.out.println("HELL2O");
            System.out.println(record.getHeroName());
            System.out.println("HELLO3");
        });
        System.out.println("HE4LLO");

        session.getTransaction().commit();
        session.close();
    }

    // TODO: No main, refactor
    public static void main(String[] args) {

        try (SessionFactory factory = new Configuration()
                .configure()
                .buildSessionFactory()) {

            fetchCombatSetup(factory);

//            fetchHeroAndCombatSetupSuccessfulTest(factory); TODO: Successful!
        }
    }
}
