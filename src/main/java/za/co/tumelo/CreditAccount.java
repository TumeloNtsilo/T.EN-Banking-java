package za.co.tumelo;

public class CreditAccount extends Account {
    private final long creditLimit;


    public CreditAccount(long initialBalance, long creditLimit) {
        super(CreateAccount.getAccountNumber(), initialBalance);
        this.creditLimit = creditLimit;
    }

    public long getCreditLimit() {
        return creditLimit;
    }

    @Override
    public long withdraw(long money) {
        long availableBalance = getMoney() + creditLimit;
        if (money > availableBalance) {
            return 0;
        }
        setMoney(getMoney() - money);
        return money;
    }

    @Override
    public String getAccountType() {
        return "Credit Account";
    }
}
