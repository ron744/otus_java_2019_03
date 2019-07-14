import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;


public class HibernateDemo {
    private final SessionFactory sessionfactory;

    public HibernateDemo() {
        Configuration configuration = new Configuration()
                .configure("hibernate.cfg.xml");

        StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties()).build();

        Metadata metadata = new MetadataSources(serviceRegistry)
                .addAnnotatedClass(User.class)
                //.addAnnotatedClass(AddressDataSet.class)
                .getMetadataBuilder()
                .build();

        sessionfactory = metadata.getSessionFactoryBuilder().build();
    }

    public void entityExample(){
        try(Session session = sessionfactory.openSession()){
            session.beginTransaction();

            User user = new User();
        }
    }

}
