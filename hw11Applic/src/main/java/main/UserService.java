package main;

import dao.UserDAO;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class UserService extends SessionUtil implements UserDAO {

    @Override
    public void add(User user){
        System.out.println("add");
        openTransactionSession();

        Session session = getSession();
            session.save(user);

        System.out.println(user.getName() + " " + user.getAge());

        closeTransactionSession();
    }

    @Override
    public List<User> getAll(){
        System.out.println("getAll");
        openTransactionSession();

        String hql = "FROM User";

        Session session = getSession();
        Query query = session.createQuery(hql);
        List<User> userList = query.list();

        closeTransactionSession();
        return userList;
    }
}
