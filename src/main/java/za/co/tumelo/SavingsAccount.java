package za.co.tumelo;

public class SavingsAccount extends Account {

    public SavingsAccount(){
        super();
    }

    @Override
    public long withdraw(long money) {
        if(viewBalance() < 20){
            System.out.println("A minimum of 20 rands is needed to withdraw.");
            return 0;
        }
        if(money > getMoney()){
            System.out.println("Insufficient amount");
            return 0;
        }
        setMoney(getMoney() - money);
        System.out.println(viewBalance());
        return money;
    }

    @Override
    public String getAccountType() {
        return "Savings Account";
    }
}
