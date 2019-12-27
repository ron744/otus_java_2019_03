import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

public class Myson {

    private String string = "";

    public String createMyson(Object object) throws IllegalAccessException {
        if (object == null)
            return null;

        Class clazz = object.getClass();
//        System.out.println(clazz.getSimpleName());

        if (clazz.isArray()){
            int length = Array.getLength(object);
            Object[] arrayObjects = new Object[length];
            string += "[";
            for (int i = 0; i < length; i++){
                arrayObjects[i] = Array.get(object, i);
                createMyson(arrayObjects[i]);
                if (i < length - 1)
                    string += ",";
            }
            string += "]";
        } else if (clazz.getSimpleName().equals("ArrayList")) {
            List<Object> listObjects = (ArrayList<Object>) object;
            int length = listObjects.size();
            string += "[";
            for (int i = 0; i < length; i++) {
                createMyson(listObjects.get(i));
                if (i < length - 1)
                    string += ",";
            }
            string += "]";

        } else if (object.getClass().getSimpleName().equals("String")) {
            string += "\"" + object + "\"";
        } else if (object.getClass().getSimpleName().equals("Byte")) {
            string += "" + object + "";
        } else if (object.getClass().getSimpleName().equals("Short")) {
            string += "" + object + "";
        } else if (object.getClass().getSimpleName().equals("Integer")) {
            string += "" + object + "";
        } else if (object.getClass().getSimpleName().equals("Long")) {
            string += "" + object + "";
        } else if (object.getClass().getSimpleName().equals("Double")) {
            string += "" + object + "";
        } else if (object.getClass().getSimpleName().equals("Float")) {
            string += "" + object + "";
        } else if (object.getClass().getSimpleName().equals("Character")) {
            string += "\"" + object + "\"";
        }
        else {
            Field[] fields = clazz.getDeclaredFields();
            int length = fields.length;
            string += "{";
            for (int i = 0; i < length; i++){
                fields[i].setAccessible(true);
                string += "\"" + fields[i].getName() + "\":";
                createMyson(fields[i].get(object));
                if (i < length - 1)
                    string += ",";
            }
            string += "}";
        }
        return string;
    }
}
