import java.util.ArrayList;
import java.util.List;

public class Department {
    private List<ATM> listATM = new ArrayList<>();

    public void getMoneyFromATM(int index, int count){
        ATM atm = listATM.get(index);
        atm.getMoney(count);
    }

    public void putMoneyInATM(int index, int nominal, int count){
        ATM atm = listATM.get(index);
        atm.putMoney(nominal, count);
        listATM.set(index, atm);
    }

    public void addATM(ATM newATM){

        if (listATM.size() > 0) {
            ATM oldATM = listATM.get(listATM.size() - 1);
            listATM.add(newATM);
            oldATM.setNextChainLink(newATM);
        } else {
            listATM.add(newATM);
        }
    }

    public void departmentBalance(){
        ATM atm = listATM.get(0);
        atm.requestBalance();
    }

    public ATM getATM(int index) {
        return listATM.get(index);
    }
}
