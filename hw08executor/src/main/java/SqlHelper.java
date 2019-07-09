import java.lang.reflect.Field;

public class SqlHelper {

    public String getSelectedById(int id, Object objectData){
        Class clazz = objectData.getClass();
        Field[] fields = clazz.getDeclaredFields();
        String sqlRequest = "select ";
        for (Field field : fields){
            sqlRequest += field.getName() + ",";
        }
        sqlRequest = sqlRequest.substring(0, sqlRequest.length() - 1);
        sqlRequest += " from " + clazz.getName().toLowerCase() + " where id = " + id;
        return sqlRequest;
    }

    private int getId(Object objectData) throws IllegalAccessException {
        Class clazz = objectData.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields){
            if (field.isAnnotationPresent(ID.class)){
                field.setAccessible(true);
                System.out.println("field.get(objectData): " + field.get(objectData));
                return (int) field.get(objectData);
            }
        }
        return 0;
    }

    public String getTable(Object objectData){
        Class clazz = objectData.getClass();
        Field[] fields = clazz.getDeclaredFields();
        //if (checkAvailableID(fields)) {
        String sqlRequest = "create table " + clazz.getName().toLowerCase() + "(";
        for (int i = 0; i < fields.length; i++) {
            String fieldName = fields[i].getName();
            String fieldType = "";

            if (fields[i].isAnnotationPresent(ID.class)){
                fieldType = "int(20) NOT NULL AUTO_INCREMENT";
            }
            if (fields[i].isAnnotationPresent(TypeString.class)){
                fieldType = "varchar(100)";
            }
            if (fields[i].isAnnotationPresent(TypeInt.class)){
                fieldType = "int(10)";
            }
            if (i + 1 == fields.length){
                sqlRequest += fieldName + " " + fieldType + ")";
            } else {
                sqlRequest += fieldName + " " + fieldType + ", ";
            }
        }
        return sqlRequest;
    }

    public String getUpdate(Object objectData) throws IllegalAccessException {
        Class clazz = objectData.getClass();
        Field[] fields = clazz.getDeclaredFields();

        String sqlRequest = "update " + clazz.getName().toLowerCase() + " set ";
        for (Field field : fields) {
            if (field.isAnnotationPresent(ID.class))
                continue;

            String fieldName = field.getName();

            field.setAccessible(true);
            if (field.getType().equals(String.class)) {
                //System.out.println(fieldName);
                sqlRequest += fieldName + " = " + "\'" + field.get(objectData) + "\'" + ",";
            } else {
                sqlRequest += fieldName + " = " + field.get(objectData) + ",";
            }
        }
        sqlRequest = sqlRequest.substring(0, sqlRequest.length() - 1);

        int id = getId(objectData); // Этот метод надо написать
        sqlRequest += " WHERE id = " + id;

        return sqlRequest;
    }

    public String getInsert(Object objectData){
        Class clazz = objectData.getClass();
        Field[] fields = clazz.getDeclaredFields();
        String sqlRequest = "insert into " + clazz.getName().toLowerCase() + "(";

        for (int i = 0; i < fields.length; i++){
            String fieldName = fields[i].getName();
            if (fields[i].isAnnotationPresent(ID.class))
                continue;

            sqlRequest += fieldName;
            //if (i != fields.length - 1)
            sqlRequest += ",";
        }

        sqlRequest = sqlRequest.substring(0, sqlRequest.length() - 1);
        sqlRequest += ") values (";

        for (int i = 1; i < fields.length; i++){
            //if (i != fields.length - 1)
            sqlRequest += "?,";
        }

        sqlRequest = sqlRequest.substring(0, sqlRequest.length() - 2);
        sqlRequest += "?)";
        return sqlRequest;
    }
}
