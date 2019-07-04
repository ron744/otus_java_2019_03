import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Bag1 bag = new Bag1();
        bag.setI(6);
        bag.setMass(new String[]{"9", "aaa"});
        bag.setMassObjects(new Object[]{new String("qwe"), new String("qwe1")});
        List<Integer> list = new ArrayList<Integer>();
        list.add(222);
        list.add(333);
        bag.setString("stringString");
        bag.setList(list);


        MyGson myGson = new MyGson();
        myGson.inGson(bag);

        Gson gson = new GsonBuilder().setPrettyPrinting().create();
        String json = gson.toJson(bag);

        System.out.println(json);


    }
}
