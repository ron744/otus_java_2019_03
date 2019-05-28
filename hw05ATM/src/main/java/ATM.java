public class ATM {
    private int balance;
    private int count50;
    private int count100;
    private int count200;

    ATM(){
        count50 = 0;
        count100 = 0;
        count200 = 0;
        balance = 0;
    }

    public void getBalance() {
        System.out.println("Current balance: " + balance);
    }
    public void putMoney(int money){
        if (money % 50 == 0) {
            balance += money;
            if (money >= 200) {
                int help = money % 200;
                count200 = (money - help) / 200;
                money = help;
            }
            if (money >= 100) {
                count100++;
                money -= 100;
            }
            if (money == 50) {
                count50++;
            }
        } else {
            System.out.println("Incorrect operation");
        }
    }
    public void getMoney(int money){
        if ((money % 50 == 0) && (money <= balance)) {
            if (money >= 200) {
                int help = money % 200;
                System.out.println("count 200 * " + (money - help) / 200);
                balance -= money - help;
                money = help;
            }
            if (money >= 100) {
                System.out.println("count 100 * 1");
                balance -= 100;
                money -= 100;
            }
            if (money == 50) {
                System.out.println("count 50 * 1");
                balance -= 50;
                money -= 50;
            }
        } else {
            System.out.println("Incorrect operation");
        }

    }

}
