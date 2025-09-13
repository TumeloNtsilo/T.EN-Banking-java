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
            System.out.println("Insufficient funds, credit limit reached.");
            return 0;
        }
        setMoney(getMoney() - money);
        System.out.println("Withdrawal successful. Current balance: " + viewBalance());
        return money;
    }

    @Override
    public String getAccountType() {
        return "Credit Account";
    }
}
