import java.sql.SQLException;
import java.util.List;

public interface UserDAO {

    void add(User user) throws SQLException;

    List<User> getAll() throws SQLException;
}
