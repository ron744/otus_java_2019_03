import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ATM {
    private List<Rub> list = new ArrayList<>();

    ATM(Integer... args){
        for (int i : args){
            list.add(new Rub(i, 0));
        }
        Collections.sort(list, new Comparator<Rub>() {
            @Override
            public int compare(Rub o1, Rub o2) {
                return o2.getNominal() - o1.getNominal();
            }
        });
    }

    public void putMoney(int nominal, int count){
        for (Rub r : list){
            if (r.getNominal() == nominal){
                r.setCount(count);
                break;
            }
        }
    }

    public void getMoney(int count){
        if (balance() > count){
            int index = 0;
            for (Rub r : list){
                while (count - r.getNominal() >= 0){
                    index++;
                    count -= r.getNominal();
                }
                r.setCount(-index);
                System.out.println("Выдано " + index + " купюр наминалом " + r.getNominal());
                index = 0;
            }
            System.out.println("-------------------------------------");

        }else {
            System.out.println("Incorrect operation!!");
        }
    }

    public int balance(){
        int balance = 0;
        for (Rub r : list) {
            balance += r.getNominal() * r.getCount();
        }
        return balance;
    }
    //public boolean check(int nominal){return true;}//Проверить, что купюра равноа 50, 100 или 200

}
