public class ATMDepartment {
    public static void main(String[] args) {

        Department department = new Department();
        department.addATM(new ATM());
        department.addATM(new ATM());
        department.addATM(new ATM());

        department.putMoneyInATM(0, 100, 3);
        department.putMoneyInATM(1, 200, 1);
        department.putMoneyInATM(2, 100, 29);
        CareTaker careTaker1 = new CareTaker();
        careTaker1.setSave(department.getATM(0).save());

        System.out.println("Main balance: " + department.getATM(0).balance());

        department.getMoneyFromATM(0, 100);
        department.getMoneyFromATM(2, 700);

        department.getATM(0).load(careTaker1.getSave());
        System.out.println("Main balance: " + department.getATM(0).balance());

        department.departmentBalance();
    }
}
