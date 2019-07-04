import java.util.ArrayList;
import java.util.List;

public class Bag1 {

    private int index;
    private String[] mass;
    private Object[] massObjects;
    private List<Integer> list = new ArrayList<Integer>();
    private String string;


    public void setString(String string) {
        this.string = string;
    }

    public void setI(int index) {
        this.index = index;
    }

    public void setMass(String[] mass) {
        this.mass = mass;
    }

    public void setMassObjects(Object[] massObjects) {
        this.massObjects = massObjects;
    }

    public void setList(List<Integer> list) {
        this.list = list;
    }
}
