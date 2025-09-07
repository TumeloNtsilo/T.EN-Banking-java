package za.co.tumelo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SavingsAccountTest {

    @Test
    void testWithdraw() {
        SavingsAccount account = new SavingsAccount();
        account.withdraw(200);
        assertEquals(800, account.viewBalance());
    }

    @Test
    void testAccountType() {
        SavingsAccount account = new SavingsAccount();
        assertEquals("Savings Account", account.getAccountType());
    }

    @Test
    void testDeposit(){
        SavingsAccount account = new SavingsAccount();
        account.deposit(400);
        assertEquals(1400, account.viewBalance());
    }
}