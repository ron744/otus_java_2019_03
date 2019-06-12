public class Main {
    public static void main(String[] args) {

        ATM atm1 = new ATM();
        ATM atm2 = new ATM();
        ATM atm3 = new ATM();

        atm1.putMoney(100, 2);
        CareTaker careTaker = new CareTaker();
        careTaker.setSave(atm1.save());

        System.out.println("Main balance: " + atm1.balance());

        atm1.getMoney(100);

        atm1.load(careTaker.getSave());
        System.out.println("Main balance: " + atm1.balance());

        //atm1.nextBalance(atm2);
        //atm2.nextBalance(atm3);
        //atm1.requestBalance();
    }
}
