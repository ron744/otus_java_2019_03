import java.sql.*;

public class H2Demo {

    public static void main(String[] args) throws SQLException{
        User user = new User();
        user.setId(1);
        user.setName("Vasya");
        user.setAge(9);
        Account account = new Account();
        DBService serviceUser = new JdbcTemplate();
        serviceUser.create(user);
        serviceUser.update(user);
        serviceUser.load(1, user);
    }
}
