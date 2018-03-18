package database;

import database.model.*;
import database.value.AccountHeroSetup;
import database.value.HeroSetup;
import game.components.StatComponent;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;

import java.util.List;

public class DBConnector {

    // TODO: For the "no red line in code' purposes
    public static StatComponent fetchStats(int playerId) {
        // TODO: MAKE THIS CLASS NOT STATIC!!! AND YOU KNOW WHAT TO DO HERE!
        return new StatComponent(10,10,10,10,10);
    }


    // TODO: For the test purposes (Raw method. delete)
    public static List<HeroSetup> fetchCombatSetupValue(SessionFactory factory) {
        List<HeroSetup> groupList;
        Session session = factory.getCurrentSession();
        session.beginTransaction();
        Query query = session.createNativeQuery("SELECT HeroClassifier.hero_name as heroName, Stat.intelligence as intelligence," +
                " Stat.strength as strength, Stat.agility as agility\n" +
                "FROM Stat, CombatSetup, Account, HeroClassifier, Hero \n" +
                "WHERE Account.ID = CombatSetup.acc_id AND \n" +
                "HeroClassifier.id = Hero.hero_id AND \n" +
                "Stat.id = Hero.stat_id AND \n" +
                "(Hero.id = CombatSetup.slot1_hero_id OR \n" +
                "Hero.id = CombatSetup.slot2_hero_id OR \n" +
                "Hero.id = CombatSetup.slot3_hero_id) AND \n" +
                "Account.ID = 1;", "HeroSetupMapping");
        //query.setParameter("id", id);
        groupList = query.getResultList();
        for ( HeroSetup hero : (List<HeroSetup>) groupList ) {
            System.out.println(hero);
        }
        session.getTransaction().commit();
        session.close();
        return groupList;
    }

    // TODO: For the test purposes
    private static void fetchAccount(SessionFactory factory) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        List<Account> results = session.createNativeQuery("SELECT a.id, a.username, a.pwd FROM Account a", Account.class).getResultList();

        for (Account acc:results) {
            System.out.println(acc);
        }

        session.getTransaction().commit();
        session.close();
    }

    // TODO: For the test purposes
    private static void fetchHero(SessionFactory factory) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        List<Hero> results = session.createNativeQuery("SELECT h.id, h.hero_id as heroId, h.stat_id, h.equipment_id FROM Hero h", Hero.class).getResultList();

        for (Hero acc:results) {
            System.out.println(acc);
        }

        session.getTransaction().commit();
        session.close();
    }

    // TODO: For the test purposes
    private static void fetchHeroAndCombatSetupSuccessfulTest(SessionFactory factory) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        //"SELECT a.id, a.username, a.pwd, cs.id as cs_id, cs.acc_id," +
        //                " cs.slot1_hero_id, cs.slot2_hero_id, cs.slot3_hero_id FROM Account a JOIN CombatSetup cs ON a.id=cs.acc_id"
        List<AccountHeroSetup> results = session.getNamedNativeQuery("SuccessfulTestQuery").getResultList();

        results.stream().forEach((record) -> {
            System.out.println(record);
//            Account a = (Account)record[0];
//            CombatSetup cs = (CombatSetup)record[1];
//            System.out.println(a);
//            System.out.println(cs);
        });

//        for (Stat acc:results) {
//            System.out.println(acc);
//        }

        session.getTransaction().commit();
        session.close();
    }

    // TODO: Working crap. Has to be refactored a bit (return type)
    private static void fetchCombatSetup(SessionFactory factory) {
        Session session = factory.getCurrentSession();
        session.beginTransaction();

        //"SELECT a.id, a.username, a.pwd, cs.id as cs_id, cs.acc_id," +
        //                " cs.slot1_hero_id, cs.slot2_hero_id, cs.slot3_hero_id FROM Account a JOIN CombatSetup cs ON a.id=cs.acc_id"
        List<HeroSetup> results = session.getNamedNativeQuery("HeroCombatQuery").getResultList();

        results.stream().forEach((record) -> {
            System.out.println(record);
//            Account a = (Account)record[0];
//            CombatSetup cs = (CombatSetup)record[1];
//            System.out.println(a);
//            System.out.println(cs);
        });

//        for (Stat acc:results) {
//            System.out.println(acc);
//        }

        session.getTransaction().commit();
        session.close();
    }

    // TODO: No main, refactor
    public static void main(String[] args) {
//        Configuration conf = new Configuration().configure();
////        conf.configure("hibernate.cfg.xml");
//        conf.addAnnotatedClass(Student.class);
//        SessionFactory factory = conf.buildSessionFactory();
//

//        Configuration configuration = new Configuration().configure();
//        SessionFactory sessionFactory = configuration.buildSessionFactory();

//        Session session = factory.getCurrentSession();


        try (SessionFactory factory = new Configuration()
                .configure()
                .buildSessionFactory()) {

//            fetchAccount(factory);
//            fetchHero(factory);
            fetchCombatSetup(factory);

//            fetchHeroAndCombatSetupSuccessfulTest(factory); TODO: Successful!

//            fetchCombatSetup(factory);

//            System.out.println("Creating new student object");
//            Student tempStudent = new Student(111, "bbb", 222, 333);
//
//            session.beginTransaction();
//
//            System.out.println("Saving...");
//            session.save(tempStudent);
//
//            session.getTransaction().commit();
//
//            System.out.println("done!");

        }
    }
}

//@Entity
//@Table(name="test")
//@NoArgsConstructor
//@AllArgsConstructor
//class Student {
//
//    @Id
//    @Column(name="first")
//    private int first;
//
//    @Column(name="second")
//    private String second;
//
//    @Column(name="third")
//    private int third;
//
//    @Column(name="fourth")
//    private int fourth;
//}