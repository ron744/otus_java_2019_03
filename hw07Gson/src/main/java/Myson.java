import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.*;

public class Myson {

    private StringBuilder result = new StringBuilder();

    public String createMyson(Object object) throws IllegalAccessException {
        if (object == null)
            return null;

        Class clazz = object.getClass();
//        System.out.println(clazz.getName());

        if (clazz.isArray()){
            parsingArray(object);
        } else if (Collection.class.isAssignableFrom(clazz)) {
            parsingCollection(object);

        } else if (object instanceof String) {
            result.append("\"" + object + "\"");
//            result += "\"" + object + "\"";
        } else if (object instanceof Byte) {
            result.append("" + object + "");
//            result += "" + object + "";
        } else if (object instanceof Short) {
            result.append("" + object + "");
//            result += "" + object + "";
        } else if (object instanceof Integer) {
            result.append("" + object + "");
//            result += "" + object + "";
        } else if (object instanceof Long) {
            result.append("" + object + "");
//            result += "" + object + "";
        } else if (object instanceof Double) {
            result.append("" + object + "");
//            result += "" + object + "";
        } else if (object instanceof Float) {
            result.append("" + object + "");
//            result += "" + object + "";
        } else if (object instanceof Character) {
            result.append("\"" + object + "\"");
//            result += "\"" + object + "\"";
        }
        else {
            parsingOwnObject(object);
        }
        return result.toString();
    }

    private void parsingArray(Object object) throws IllegalAccessException {
        int length = Array.getLength(object);
        Object[] arrayObjects = new Object[length];
        result.append("[");
//        result += "[";
        for (int i = 0; i < length; i++){
            arrayObjects[i] = Array.get(object, i);
            createMyson(arrayObjects[i]);
            if (i < length - 1)
                result.append(",");
//                result += ",";
        }
        result.append("]");
//        result += "]";
    }

    private void parsingCollection(Object object) throws IllegalAccessException {
        result.append("[");
//        result += "[";
        Iterator<Object> iterator = ((Collection<Object>) object).iterator();
        int length = ((Collection<Object>) object).size();
        int i = 0;
        while (iterator.hasNext()){
            createMyson(iterator.next());
            if (i < length - 1) {
                result.append(",");
//                result += ",";
                i++;
            }
        }
        result.append("]");
//        result += "]";
    }

    private void parsingOwnObject(Object object) throws IllegalAccessException {
        Class clazz  = object.getClass();
        Field[] fields = clazz.getDeclaredFields();
        int length = fields.length;
        result.append("{");
//        result += "{";
        for (int i = 0; i < length; i++){
            fields[i].setAccessible(true);
            result.append("\"" + fields[i].getName() + "\":");
//            result += "\"" + fields[i].getName() + "\":";
            createMyson(fields[i].get(object));
            if (i < length - 1)
                result.append(",");
//                result += ",";
        }
        result.append("}");
//        result += "}";
    }
}
