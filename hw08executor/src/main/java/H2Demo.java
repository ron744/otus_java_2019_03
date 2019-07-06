import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class H2Demo {

    public static void main(String[] args) throws SQLException, NoSuchMethodException, IllegalAccessException, InstantiationException, InvocationTargetException {
        User user = new User("Vasya", 9);
        //Account account = new Account();
        DBService serviceUser = new JdbcTemplate();
        serviceUser.createTable(user);
        serviceUser.insert(user);
        user.setAge(10);
        serviceUser.update(user);
        User loadedUser = serviceUser.load(1, user);
        System.out.println("loadedUserAge: " + loadedUser.getAge());
    }
}
