import java.util.ArrayList;
import java.util.List;

public class CasseteBay {

    private List<MoneyCell> moneyCells;

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
