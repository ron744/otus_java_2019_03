
public class CareTaker {
    private Save save;
    public Save getSave(){
        System.out.println("Loading...");
        for (MoneyCell m : save.getSaveMoneyCells()){
            System.out.println("Банкнота " + m.getBanknote() + " количество купюр " + m.getCount());
        }
        return save;}

    public void setSave(Save save) {
        System.out.println("Saving...");
        for (MoneyCell m : save.getSaveMoneyCells()){
            System.out.println("Банкнота " + m.getBanknote() + " количество купюр " + m.getCount());
        }
        this.save = save;
    }
}

