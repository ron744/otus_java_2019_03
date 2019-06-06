import java.util.ArrayList;
import java.util.List;

public class CasseteBay {

    private List<MoneyCell> listOfMoneyCell = new ArrayList<>();
    private Banknote[] banknotes = Banknote.values();

    public List<MoneyCell> createMoneyCells(){
        for (Banknote b : banknotes){
            listOfMoneyCell.add(new MoneyCell(b, 0));
        }
        return listOfMoneyCell;
    }


}
