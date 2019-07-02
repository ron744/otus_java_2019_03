import java.sql.SQLException;

public interface DBService {

    <T> void createTable(T objectData) throws SQLException;
    <T> void insert(T objectData) throws SQLException;
    <T> void update(T objectData) throws SQLException;
    <T> void load(int id, T objectData) throws SQLException;
}
