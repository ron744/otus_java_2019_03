package main;

import dao.UserDAO;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class UserService implements UserDAO {

    public Session session;

    @Override
    public void add(User user){
        System.out.println("add");
        session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();

        try {
            session.save(user);
            session.getTransaction().commit();
        }catch(Exception e){
            session.getTransaction().rollback();
        }
        session.close();

        System.out.println(user.getName() + " " + user.getAge());
    }

    @Override
    public List<User> getAll(){
        System.out.println("getAll");
        List<User> userList = null;
        session = HibernateUtils.getSessionFactory().openSession();
        session.beginTransaction();
        String hql = "FROM User";

        try {
            Query query = session.createQuery(hql);
            userList = query.list();
            session.getTransaction().commit();
        }catch(Exception e){
            session.getTransaction().rollback();
        }
        session.close();

        return userList;
    }
}
