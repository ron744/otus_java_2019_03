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
                .addAnnotatedClass(AddressDataSet.class)
                .addAnnotatedClass(Phone.class)
                .getMetadataBuilder()
                .build();

        sessionfactory = metadata.getSessionFactoryBuilder().build();
    }

    public void entityExample(){
        try(Session session = sessionfactory.openSession()){
            session.beginTransaction();

            Phone phone1 = new Phone();
            phone1.setNumber(123);
            Phone phone2 = new Phone();
            phone2.setNumber(456);

            AddressDataSet address = new AddressDataSet();
            address.setStreet("Arbat");

            User user = new User();
            user.setName("Tolik");
            user.setAge(46);
            user.setAddress(address);
            user.getPhones().add(phone1);
            user.getPhones().add(phone2);

            session.save(user);
            session.save(phone1);
            session.save(phone2);
            session.save(address);

            session.getTransaction().commit();
            System.out.println(user.getAddress().getStreet());
            //User selected = session.get(User.class, user.getId());
            //System.out.println(selected);
        }
    }
}
