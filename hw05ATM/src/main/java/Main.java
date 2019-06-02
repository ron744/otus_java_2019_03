public class Main {
    public static void main(String[] args) {
        ATM atm = new ATM(500, 50, 200, 100);
        atm.putMoney(100, 5);
        atm.getMoney(250);
        atm.putMoney(300, 1);
        atm.putMoney(500, 2);
        atm.getMoney(950);
        System.out.println("Balance: " + atm.balance() + " Rub");

    }
}
