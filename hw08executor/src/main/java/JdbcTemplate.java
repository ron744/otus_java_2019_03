import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.sql.*;

public class JdbcTemplate implements DBService{

    private static final String URL = "jdbc:h2:mem:test";
    private final Connection connection;

    public JdbcTemplate() throws SQLException{
        this.connection = DriverManager.getConnection(URL);
        this.connection.setAutoCommit(false);
    }

    @Override
    public void createTable(Object objectData) throws SQLException {

        SqlHelper sql = new SqlHelper();
        String sqlRequest = sql.getTable(objectData);
            System.out.println(sqlRequest);
            try (PreparedStatement pst = connection.prepareStatement(sqlRequest)) {
                pst.executeUpdate();
            }
            System.out.println("-----table create-----");
    }

    @Override
    public void insert(Object objectData) throws SQLException{
        SqlHelper sql = new SqlHelper();
        String sqlRequest = sql.getInsert(objectData);

            System.out.println(sqlRequest);

            try (PreparedStatement pst = connection.prepareStatement(sqlRequest, Statement.RETURN_GENERATED_KEYS)) {
                Class clazz = objectData.getClass();
                Field[] fields = clazz.getDeclaredFields();
                int i = 1;
                for (Field field : fields){
                    field = clazz.getDeclaredField(field.getName());
                    field.setAccessible(true);
                    if (field.isAnnotationPresent(ID.class))
                        continue;

                    if (field.getType().getName().equals("int")){
                        //System.out.println(field.get(objectData));
                        pst.setObject(i , field.get(objectData));
                    }

                    if (field.getType().getName().equals("java.lang.String")){
                        //System.out.println(field.get(objectData));
                        pst.setObject(i , field.get(objectData));
                    }
                    i++;
                }
                pst.executeUpdate();
                ResultSet rs = pst.getGeneratedKeys();
                //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
                if (rs.next()){
                    for (i = 0; i < fields.length; i++) {
                        fields[i].setAccessible(true);
                        if (fields[i].isAnnotationPresent(ID.class)){

                            fields[i].set(objectData, rs.getInt(1));
                            break;
                        }
                    }
                }
                //!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!
            } catch (IllegalAccessException | NoSuchFieldException e) {
                e.printStackTrace();
            }
            System.out.println("-----table insert-----");
    }

    @Override
    public void update(Object objectData) throws SQLException, IllegalAccessException {

        SqlHelper sql = new SqlHelper();
        String sqlRequest = sql.getUpdate(objectData);

        try (PreparedStatement pst = connection.prepareStatement(sqlRequest)) {
            Savepoint savepoint = connection.setSavepoint("savePoint");

            try {
                int rowCount = pst.executeUpdate();
                connection.commit();
                System.out.println("updated rowCount: " + rowCount);
            } catch (SQLException e){
                connection.rollback(savepoint);
                System.out.println(e.getMessage());
            }
        }
        System.out.println("-----table update-----");
    }

    @Override
    public <T> T load(int id, T objectData) throws SQLException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {

        Class clazz = objectData.getClass();
        Constructor constructor = clazz.getConstructor();
        Object user = constructor.newInstance();
        Field[] objectFields = user.getClass().getDeclaredFields();

            SqlHelper sql = new SqlHelper();
            String sqlRequest = sql.getSelectedById(id, objectData);

            try (PreparedStatement pst = connection.prepareStatement(sqlRequest)) {

                try (ResultSet rs = pst.executeQuery()){

                    if (rs.next()){
                        int i = 1;
                        for (Field field : objectFields){
                            field.setAccessible(true);
                            field.set(user, rs.getObject(i));
                            i++;
                        }
                        System.out.println(rs.getObject(1));
                        System.out.println(rs.getObject(2));
                        System.out.println(rs.getObject(3));
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        return (T) user;
    }

    /*private boolean checkAvailableID(Field[] fields){
        for (Field field : fields){
            if (field.isAnnotationPresent(ID.class)){
                return true;
            }
        }
        return false;
    }*/
}
