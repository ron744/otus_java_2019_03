package ru.otus.homework.services;

import ru.otus.homework.model.User;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class UserServiceImpl implements UserService {

    private Session session;
    private HibernateUtils hibernateUtils = new HibernateUtils();

    @Override
    public void add(User user){
        System.out.println("add");
        //session = HibernateUtils.getSessionFactory().openSession();
        session = hibernateUtils.getSessionFactory().openSession();
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
        //session = HibernateUtils.getSessionFactory().openSession();
        session = hibernateUtils.getSessionFactory().openSession();
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
