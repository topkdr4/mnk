package util;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;





/**
 * Ветошкин А.В. РИС-16бзу
 * */
public final class HibernateSession {


    private static final SessionFactory sessionFactory;

    static {
        Configuration configuration = new Configuration();
        configuration.configure();
        sessionFactory = configuration.buildSessionFactory();
    }


    public static SessionFactory getHibernateSessionFactory() {
        return sessionFactory;
    }

}
