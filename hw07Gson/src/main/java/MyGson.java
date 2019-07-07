import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.util.Collection;
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
                //System.out.println("fields[i].get(objectData):  " + fields[i].get(objectData));
{
                    //String podString = fields[i].getType().getSimpleName();
                    if (fields[i].getType().isArray()){
                        //System.out.println("ARRAY");
                        //inGson(fields[i]);
                    }

                    if (fields[i].getType().getName().equals("java.util.List")){
                        //System.out.println("LIST");

                        //generalString += "\"" + fields[i].getName() + "\":{";
                        List<Object> listObjects = (List<Object>) fields[i].get(objectData);
                        for (int j = 0; j < listObjects.size(); j++){
                            inGson(listObjects.get(j));
                            System.out.println(listObjects.get(j).getClass().getName());
                            System.out.println(listObjects.get(j));
                            //generalString += "\"" + listObjects.get(j) + "\"";
                            //checkEndMass(j, listObjects.size());
                        }
                    }

                    //System.out.println(fields[i].getType().getName());

                    if (fields[i].getType().getName().equals("java.lang.String")){
                        //System.out.println("STRING");
                        generalString += "\"" + fields[i].getName() + "\"" + ":" + fields[i].get(objectData);
                        checkEndMass(i, fields.length);
                    }

                    if (fields[i].getType().getName().equals("java.lang.Object")){
                        //System.out.println("OBJECT");
                    }

                    if (fields[i].getType().getName().equals("int")){
                        //System.out.println("int");
                        System.out.println("int: " + fields[i].get(objectData));
                        //System.out.println("int  " + fields[i].get(objectData));
                        generalString += "\"" + fields[i].getName() + "\"" + ":" + fields[i].get(objectData);
                        checkEndMass(i, fields.length);
                    }

                    if (fields[i].getType().getName().equals("java.lang.Integer")){
                        System.out.println("INTEGER");
                        //System.out.println("INTEGER  " + fields[i].get(objectData));
                        generalString += "\"" + fields[i].getName() + "\"" + ":" + fields[i].get(objectData);
                        checkEndMass(i, fields.length);
                    }









                    /*if (podString.contains("String")) {
                        if (fields[i].getType().isArray()) {
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
                        if (fields[i].getType().isArray()) {
                            generalString += "\"" + fields[i].getName() + "\":{";
                            Object[] objects = (Object[]) fields[i].get(objectData);

                            for (int j = 0; j < objects.length; j++){
                                generalString += "\"" + objects[j].toString() + "\"";
                                checkEndMass(j, objects.length);
                            }
                        } else {

                        }
                    } else if (Collection.class.isAssignableFrom(fields[i].getType())){
                        generalString += "\"" + fields[i].getName() + "\":{";
                        List<Object> listObjects = (List<Object>) fields[i].get(objectData);
                        for (int j = 0; j < listObjects.size(); j++){
                            generalString += "\"" + listObjects.get(j) + "\"";
                            checkEndMass(j, listObjects.size());
                        }
                    }*/
                    if (i != fields.length -1){
                        //generalString += "},";
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
