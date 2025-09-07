package za.co.tumelo;

public class CreditAccount extends Account {
    private final long creditLimit;

    // Constructor that allows starting balance and credit limit
    public CreditAccount(long initialBalance, long creditLimit) {
        super(CreateAccount.getAccountNumber(), initialBalance); // sets account number and starting balance
        this.creditLimit = creditLimit;
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
