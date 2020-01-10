import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class GsonDemo {
    public static void main(String[] args) throws IllegalAccessException{

        String[] str = {"qwe", "qwe1"};
        int[] intarray = {1,2,3,4,5};
        int i = 123;
        List<Integer> list = new ArrayList<>();
        list.add(123);
        list.add(456);

        Bag obj1 = new Bag("Ivan", str, list);
        System.out.println(obj1);

        Myson demo = new Myson();
        String json1 = demo.createMyson(obj1);
        System.out.println(json1);

        Gson gson = new Gson();
        Bag obj2 = gson.fromJson(json1, Bag.class);
        System.out.println(obj2);
//        String json2 = gson.toJson(obj1);
//        System.out.println(json2);
    }
}
