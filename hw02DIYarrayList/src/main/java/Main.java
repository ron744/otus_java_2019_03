import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        List<Integer> collection1 = new DIYarrayList<Integer>();
        List<Integer> collection2 = new DIYarrayList<Integer>();
        Collections.addAll(collection1, 459, 92, 7, 9, 3, 90, 4, 2, 8, 34, 3, 444, 6, 89, 12, 0, 34, 41, 55, 78, 18, 1000);
        Collections.addAll(collection2, 1, 1, 1, 1, 1, 1, 4, 2, 8, 34, 3, 444, 6, 89, 12, 0, 34, 41, 55, 78, 18, 1000);
        System.out.println("Collection2 before change");
        for(Integer l : collection2){
            System.out.print(l + " ");
        }
        System.out.println();

        Collections.copy(collection2, collection1);
        Collections.sort(collection2, new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                if((o1 instanceof Integer) && (o2 instanceof Integer)) {
                    return o1.compareTo(o2);
                }
                return 0;
            }
        });

        System.out.println("Collection2 after change");
        for(Integer l : collection2){
            System.out.print(l + " ");
        }
        System.out.println();
        System.out.println("collection2: " + collection2.size());
    }
}
