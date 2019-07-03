import java.sql.*;

public class H2Demo {

    public static void main(String[] args) throws SQLException{
        User user = new User();
        //user.setId(1);
        user.setName("Vasya");
        user.setAge(9);
        Account account = new Account();
        DBService serviceUser = new JdbcTemplate();
        serviceUser.createTable(user);
        serviceUser.insert(user);
        user.setAge(10);
        serviceUser.update(user);
        User loadedUser = serviceUser.load(1, user);
        System.out.println("loadedUserAge: " + loadedUser.getAge());
    }
}
