import java.util.List;

public class Save {
    private final List<MoneyCell> moneyCells;
    public Save(List<MoneyCell> moneyCells){
        this.moneyCells = moneyCells;
    }
    public List<MoneyCell> getSaveMoneyCells(){
        return moneyCells;
    }

}
