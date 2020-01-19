import java.util.Arrays;
import java.util.List;

public class Bag {
    private String value;
    private String[] string;
    private List<Integer> list;

    public String getValue() {
        return value;
    }

    public String[] getString() {
        return string;
    }

    public List<Integer> getList() {
        return list;
    }

    public Bag(String value, String[] string, List<Integer> list) {
        this.value = value;
        this.string = string;
        this.list = list;
    }

    @Override
    public String toString() {
        return "Bag{" +
                "value='" + value + '\'' +
                ", string=" + Arrays.toString(string) +
                ", list=" + list +
                '}';
    }

    Bag(){}
}
