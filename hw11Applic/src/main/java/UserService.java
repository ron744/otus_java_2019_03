import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class UserService extends SessionUtil implements UserDAO {

    //private List<User> userList;

    @Override
    public void add(User user){
        //userList.add(user);
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

        String sql = "SELECT * FROM User";

        Session session = getSession();
        Query query = session.createNativeQuery(sql).addEntity(User.class);
        List<User> userList = query.list();

        closeTransactionSession();
        return userList;
    }
}
