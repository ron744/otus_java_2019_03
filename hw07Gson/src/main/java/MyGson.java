import java.lang.reflect.Field;
import java.util.List;

public class MyGson {

    private String generalString;

    public void inGson(Object objectData){
        Class clazz = objectData.getClass();
        Field[] fields = clazz.getDeclaredFields();
        generalString = "{";
        for (int i = 0; i < fields.length; i++){
            try {
                fields[i].setAccessible(true);
                if (fields[i].getType().isPrimitive()){
                    generalString += "\"" + fields[i].getName() + "\"" + ":" + fields[i].get(objectData);
                    checkEndMass(i, fields.length);

                } else {
                    String podString = fields[i].getType().getSimpleName();
                    if (podString.contains("String")) {
                        if (podString.contains("[]")) {
                            generalString += "\"" + fields[i].getName() + "\":{";
                            String[] strings = (String[]) fields[i].get(objectData);

                            for (int j = 0; j < strings.length; j++) {
                                generalString += "\"" + strings[j] + "\"";
                                checkEndMass(j, strings.length);
                            }

                        } else {
                            generalString += "\"" + fields[i].getName() + "\":\"" + fields[i].get(objectData) + "\"";
                            checkEndMass(i, fields.length);
                        }

                    } else if (podString.contains("Object")){
                        if (podString.contains("[]")) {
                            generalString += "\"" + fields[i].getName() + "\":{";
                            Object[] objects = (Object[]) fields[i].get(objectData);

                            for (int j = 0; j < objects.length; j++){
                                generalString += "\"" + objects[j].toString() + "\"";
                                checkEndMass(j, objects.length);
                            }
                        } else {

                        }
                    } else if (podString.contains("List")){
                        generalString += "\"" + fields[i].getName() + "\":{";
                        List<Object> listObjects = (List<Object>) fields[i].get(objectData);
                        for (int j = 0; j < listObjects.size(); j++){
                            generalString += "\"" + listObjects.get(j) + "\"";
                            checkEndMass(j, listObjects.size());
                        }
                    }
                    if (i != fields.length -1){
                        generalString += "},";
                    }
                }
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        generalString += "}";
        System.out.println("generalString: "+ generalString);
    }

    public String checkEndMass(int i, int length){
        if (i != length - 1)
            return generalString += ",";
        return "";
    }
}
