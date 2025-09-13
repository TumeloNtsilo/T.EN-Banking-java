package za.co.tumelo;

public class SavingsAccount extends Account {

    public SavingsAccount(){
        super();
    }

    @Override
    public long withdraw(long money) {
        if(money > getMoney()){
            return 0;
        }
        setMoney(getMoney() - money);
        return money;
    }

    @Override
    public String getAccountType() {
        return "Savings Account";
    }
}
