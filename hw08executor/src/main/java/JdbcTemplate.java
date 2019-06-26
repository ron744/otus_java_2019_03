import java.lang.reflect.Field;
import java.sql.*;

public class JdbcTemplate implements DBService{

    private static final String URL = "jdbc:h2:mem:test";
    private final Connection connection;

    public JdbcTemplate() throws SQLException{
        this.connection = DriverManager.getConnection(URL);
        this.connection.setAutoCommit(false);
    }

    @Override
    public void create(Object objectData) throws SQLException {

        Class clazz = objectData.getClass();
        Field[] fields = clazz.getDeclaredFields();
        if (fields[0].getAnnotation(ID.class) != null) {
            String sqlRequest = "create table " + clazz.getName().toLowerCase() + "(";
            for (int i = 0; i < fields.length; i++) {
                String fieldName = fields[i].getName();
                String fieldType = "";

                if (fields[i].getAnnotation(ID.class) != null){
                    fieldType = "int(20) NOT NULL auto_increment";
                }
                if (fields[i].getAnnotation(stringstring.class) != null){
                    fieldType = "varchar(100)";
                }
                if (fields[i].getAnnotation(intint.class) != null){
                    fieldType = "int(10)";
                }
                if (i + 1 == fields.length){
                    sqlRequest += fieldName + " " + fieldType + ")";
                } else {
                    sqlRequest += fieldName + " " + fieldType + ", ";
                }
            }
            System.out.println(sqlRequest);
            try (PreparedStatement pst = connection.prepareStatement(sqlRequest)) {
                pst.executeUpdate();

            }
            System.out.println("-----table create-----");
        }
    }

    @Override
    public void update(Object objectData) throws SQLException{
        Class clazz = objectData.getClass();
        Field[] fields = clazz.getDeclaredFields();
        if (fields[0].getAnnotation(ID.class) != null) {
            String sqlRequest = "insert into " + clazz.getName().toLowerCase() + "(";
            for (int i = 0; i < fields.length; i++) {
                String fieldName = fields[i].getName();
                if (i + 1 == fields.length){
                    sqlRequest += fieldName + ") ";
                } else {
                    sqlRequest += fieldName + ", ";
                }
            }
            sqlRequest += "values (";
            for (int i = 0; i < fields.length; i++){
                if (i + 1 == fields.length){
                    sqlRequest += "?)";
                } else {
                    sqlRequest += "?, ";
                }
            }
            System.out.println(sqlRequest);

            try (PreparedStatement pst = connection.prepareStatement(sqlRequest)) {
                Savepoint savepoint = connection.setSavepoint("savePoint");
                for (int i = 0; i < fields.length; i++){

                    Field field = clazz.getDeclaredField(fields[i].getName());
                    field.setAccessible(true);

                    if (fields[i].getType().getName().equals("int")){
                        pst.setInt(i + 1, (Integer) field.get(objectData));
                    }

                    if (fields[i].getType().getName().equals("java.lang.String")){
                        pst.setString(i + 1, (String) field.get(objectData));
                    }
                }
                try {
                    int rowCount = pst.executeUpdate();
                    connection.commit();
                    System.out.println("updated rowCount: " + rowCount);
                } catch (SQLException e){
                    connection.rollback(savepoint);
                    System.out.println(e.getMessage());
                }

            } catch (IllegalAccessException | NoSuchFieldException e) {
                e.printStackTrace();
            }
            System.out.println("-----table update-----");
        }
    }

    @Override
    public void load(int id, Object objectData) throws SQLException{
        Class clazz = objectData.getClass();
        Field[] fields = clazz.getDeclaredFields();
        if (fields[0].getAnnotation(ID.class) != null) {
            String sqlRequest = "select name from " + clazz.getName().toLowerCase() + " where id = ?";

            try (PreparedStatement pst = connection.prepareStatement(sqlRequest)) {
                pst.setInt(1, id);

                try (ResultSet rs = pst.executeQuery()){
                    System.out.print("name: ");
                    if (rs.next()){
                        System.out.println(rs.getString("name"));
                    }
                }
            }
        }
    }
}
