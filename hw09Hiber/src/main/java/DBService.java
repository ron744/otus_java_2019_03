import java.lang.reflect.InvocationTargetException;
import java.sql.SQLException;

public interface DBService {

    <T> void createTable(T objectData) throws SQLException;
    <T> void insert(T objectData) throws SQLException;
    <T> void update(T objectData) throws SQLException, IllegalAccessException;
    <T> T load(int id, T objectData) throws SQLException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException;
}
