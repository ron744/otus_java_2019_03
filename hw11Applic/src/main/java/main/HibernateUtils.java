package main;

import org.hibernate.SessionFactory;

import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.cfg.Configuration;

public class HibernateUtils {

    private static SessionFactory sessionFactory = buildSessionFactory();

    public static SessionFactory getSessionFactory(){
        return sessionFactory;
    }

    private static SessionFactory buildSessionFactory(){
        Configuration configuration = new Configuration()
                .configure("hibernate.cfg.xml");
        StandardServiceRegistry serviceRegistry =
                new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
        Metadata metadata = new MetadataSources(serviceRegistry)
                .addAnnotatedClass(User.class)
                .getMetadataBuilder()
                .build();

        return sessionFactory = metadata.getSessionFactoryBuilder().build();
    }
}
