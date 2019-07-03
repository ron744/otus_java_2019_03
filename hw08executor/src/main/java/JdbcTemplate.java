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
    public void createTable(Object objectData) throws SQLException {

        Class clazz = objectData.getClass();
        Field[] fields = clazz.getDeclaredFields();
        if (fields[0].getAnnotation(ID.class) != null) {
            String sqlRequest = "create table " + clazz.getName().toLowerCase() + "(";
            for (int i = 0; i < fields.length; i++) {
                String fieldName = fields[i].getName();
                String fieldType = "";

                if (fields[i].getAnnotation(ID.class) != null){
                    fieldType = "int(20) NOT NULL AUTO_INCREMENT ";
                }
                if (fields[i].getAnnotation(TypeString.class) != null){
                    fieldType = "varchar(100)";
                }
                if (fields[i].getAnnotation(TypeInt.class) != null){
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
    public void insert(Object objectData) throws SQLException{
        Class clazz = objectData.getClass();
        Field[] fields = clazz.getDeclaredFields();
        if (fields[0].getAnnotation(ID.class) != null) {
            String sqlRequest = "insert into " + clazz.getName().toLowerCase() + "(";
            for (int i = 0; i < fields.length; i++) {
                String fieldName = fields[i].getName();
                if (!fieldName.equals("id")){
                    if (i + 1 == fields.length) {
                        sqlRequest += fieldName + ") ";
                    } else {
                        sqlRequest += fieldName + ", ";
                    }
                }
            }
            sqlRequest += "values (";
            for (int i = 0; i < fields.length - 1; i++){
                if (i + 1 == fields.length - 1){
                    sqlRequest += "?)";
                } else {
                    sqlRequest += "?, ";
                }
            }

            System.out.println(sqlRequest);

            try (PreparedStatement pst = connection.prepareStatement(sqlRequest)) {
                for (int i = 1; i < fields.length; i++){

                    Field field = clazz.getDeclaredField(fields[i].getName());
                    field.setAccessible(true);
                    if (fields[i].getAnnotation(ID.class) != null){

                        ResultSet rs = pst.getGeneratedKeys();
                        int idValue = 0;
                        if (rs.next()) {
                            idValue = rs.getInt("id");
                            System.out.println("ID value: ");

                        }
                        pst.setInt(i , rs.getInt(1));
                    } else {
                        if (fields[i].getType().getName().equals("int")){
                            pst.setInt(i, (Integer) field.get(objectData));
                        }

                        if (fields[i].getType().getName().equals("java.lang.String")){
                            pst.setString(i, (String) field.get(objectData));
                        }
                    }
                }
                pst.executeUpdate();
                //connection.commit();

            } catch (IllegalAccessException | NoSuchFieldException e) {
                e.printStackTrace();
            }
            System.out.println("-----table insert-----");
        }
    }

    @Override
    public void update(Object objectData) throws SQLException{
        Class clazz = objectData.getClass();
        Field[] fields = clazz.getDeclaredFields();

        if (fields[0].getAnnotation(ID.class) != null) {
            String sqlRequest = "update " + clazz.getName().toLowerCase() + " set ";
            //try (PreparedStatement pst1 = connection.prepareStatement("select * from " + clazz.getName().toLowerCase())) {
                for (int i = 0; i < fields.length; i++) {
                    String fieldName = fields[i].getName();
                    try {
                        Field field1 = clazz.getDeclaredField(fields[i].getName());
                        field1.setAccessible(true);
                        if (!fieldName.equals("id")) {
                            if (i + 1 == fields.length) {
                                System.out.println();
                                sqlRequest += fieldName + " = " + field1.get(objectData) + " WHERE id = 1";
                            } else {
                                sqlRequest += fieldName + " = " + "\'" + field1.get(objectData) + "\'" + ", ";
                            }
                        }
                    } catch (IllegalAccessException | NoSuchFieldException e) {
                        e.printStackTrace();
                    }
                }
            //}
            System.out.println(sqlRequest);

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
    }

    @Override
    public <T> T load(int id, T objectData) throws SQLException {
        Class clazz = objectData.getClass();
        Field[] fields = clazz.getDeclaredFields();
        T loadObject = null;
        if (fields[0].getAnnotation(ID.class) != null) {
            String sqlRequest = "select * from " + clazz.getName().toLowerCase() + " where id = " + id;

            try (PreparedStatement pst = connection.prepareStatement(sqlRequest)) {

                try (ResultSet rs = pst.executeQuery()){
                    if (rs.next()){
                        System.out.println(rs.getInt("id"));
                        System.out.println(rs.getString("name"));
                        System.out.println(rs.getInt("age"));
                    }
                }
            }
        }
        return loadObject;
    }
}
