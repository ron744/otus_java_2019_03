import java.util.ArrayList;
import java.util.List;

public class CasseteBay {

    private List<MoneyCell> moneyCells;
    /*private Banknote[] banknotes = Banknote.values();

    public List<MoneyCell> createMoneyCells(){
        for (Banknote b : banknotes){
            moneyCells.add(new MoneyCell(b, 0));
        }
        return moneyCells;
    }*/
    public CasseteBay(){
        this.moneyCells = new ArrayList<>();
        Banknote[] banknotes = Banknote.values();
        for (Banknote b : banknotes){
            moneyCells.add(new MoneyCell(b, 0));
        }
    }
    public List<MoneyCell> getMoneyCells(){
        return moneyCells;
    }


}
