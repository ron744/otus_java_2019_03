import java.util.ArrayList;
import java.util.List;

public class ATM implements ChainOfResponsibility{
    private List<MoneyCell> moneyCells;
    private ChainOfResponsibility chain;

    /*ATM(int... args){
        for (int i : args){
            listOfMoneyCell.add(new MoneyCell(i, 0));
        }
        Collections.sort(listOfMoneyCell, (o1, o2) -> o2.getNominal() - o1.getNominal());
    }*/
    ATM(){
        this.moneyCells = new CasseteBay().getMoneyCells();
        //save();
    }

    @Override
    public void nextBalance(ChainOfResponsibility nextChain) {
            chain = nextChain;
    }

    @Override
    public void requestBalance() {
        System.out.println("Current balance: " + balance());
        if (chain instanceof ATM)
            chain.requestBalance();
    }

    public Save save(){

        return new Save(moneyCells);
    }
    public void load(Save save){
        moneyCells = save.getSaveMoneyCells();
    }

    public void putMoney(int nominal, int count){
        for (MoneyCell r : moneyCells){
            if (r.getBanknote().getNominel() == nominal){
                r.setCount(count);
                break;
            }
        }
    }

    public void getMoney(int count){
        if (balance() >= count){
            int index = 0;
            for (MoneyCell r : moneyCells){
                //System.out.println("for r: " + r.getBanknote());
                while ((count - r.getBanknote().getNominel() >= 0) && (r.getCount() > 0)){
                    //System.out.println("while r: " + r.getBanknote());
                    index++;
                    count -= r.getBanknote().getNominel();
                }
                r.setCount(-index);
                //System.out.println("Выдано " + index + " купюр наминалом " + r.getBanknote().getNominel());
                index = 0;
            }
            System.out.println("-------------------------------------");

        }else {
            System.out.println("Incorrect operation!!");
        }
    }

    public int balance(){
        int balance = 0;
        for (MoneyCell r : moneyCells) {
            balance += r.getBanknote().getNominel() * r.getCount();
        }
        return balance;
    }

    public void pringCellsStatus() {
        System.out.println("\nСтатус ячеек банокмата");
        moneyCells.forEach(rub -> System.out.println(String.format("Ячейка {номинал: %d, количество: %d}", rub.getBanknote().getNominel(), rub.getCount())));
    }
}
