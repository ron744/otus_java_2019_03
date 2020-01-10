import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.*;

public class Myson {

    private String string = "";

    public String createMyson(Object object) throws IllegalAccessException {
        if (object == null)
            return null;

        Class clazz = object.getClass();
//        System.out.println(clazz.getName());

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
        } else if (Collection.class.isAssignableFrom(clazz)) {
//            List<Object> listObjects = (List<Object>) object;
            string += "[";
//            for (int i = 0; i < length; i++) {
//                createMyson(listObjects.get(i));
//                if (i < length - 1)
//                    string += ",";
//            }
            Iterator<Object> iterator = ((Collection<Object>) object).iterator();
            int length = ((Collection<Object>) object).size();
            int i = 0;
            while (iterator.hasNext()){
                createMyson(iterator.next());
                if (i < length - 1) {
                    string += ",";
                    i++;
                }
            }

            string += "]";

        } else if (object instanceof String) {
            string += "\"" + object + "\"";
        } else if (object instanceof Byte) {
            string += "" + object + "";
        } else if (object instanceof Short) {
            string += "" + object + "";
        } else if (object instanceof Integer) {
            string += "" + object + "";
        } else if (object instanceof Long) {
            string += "" + object + "";
        } else if (object instanceof Double) {
            string += "" + object + "";
        } else if (object instanceof Float) {
            string += "" + object + "";
        } else if (object instanceof Character) {
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
