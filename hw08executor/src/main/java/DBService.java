import java.sql.SQLException;

public interface DBService {

    <T> void create(T objectData) throws SQLException;
    <T> void update(T objectData) throws SQLException;
    <T> void load(int id, T objectData) throws SQLException;
}
