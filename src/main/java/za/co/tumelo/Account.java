package za.co.tumelo;


public abstract class Account {
    private int accountNumber;
    private long money;

    public Account(int accountNumber, long money){
        this.accountNumber = accountNumber;
        this.money = money;
    }

    public Account() {
        this(CreateAccount.getAccountNumber(), 1000);
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public long getMoney() {
        return money;
    }

    public void setMoney(long money) {
        this.money = money;
    }

    public abstract long withdraw(long money);

    public long deposit(long money){
        this.money += money;
        return this.money;
    }

    public long viewBalance(){
        return this.money;
    }

    public abstract String getAccountType();
}
