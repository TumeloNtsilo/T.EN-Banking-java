package za.co.tumelo;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreditAccountTest {

    @Test
    void testWithdraw() {
        CreditAccount account = new CreditAccount(1000,5000);
        account.withdraw(500);
        assertEquals(500, account.viewBalance());
    }

    @Test
    void testAccountType() {
        CreditAccount account = new CreditAccount(1000, 5000);
        assertEquals("Credit Account", account.getAccountType());
    }

    @Test
    void testDeposit(){
        CreditAccount account = new CreditAccount(1000, 5000);
        account.deposit(1500);
        assertEquals(2500, account.viewBalance());
    }
}