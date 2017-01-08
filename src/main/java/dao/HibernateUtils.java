package dao;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

/**
 * Created by Kirill on 07.01.2017.
 */
public class HibernateUtils {
    private static SessionFactory sessionFactory = null;
    private static final Logger LOGGER = Logger.getLogger(HibernateUtils.class);

    private HibernateUtils() {

    }


    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            try {
                LOGGER.info("Trying to create a test connection with the database.");
                Configuration configuration = new Configuration();
                configuration.configure();
                StandardServiceRegistryBuilder ssrb = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties());
                sessionFactory = configuration.buildSessionFactory(ssrb.build());
            } catch (HibernateException ex) {
                LOGGER.error("Initial SessionFactory creation failed. ", ex);
                throw new ExceptionInInitializerError(ex);
            }
        }
        return sessionFactory;
    }
}
