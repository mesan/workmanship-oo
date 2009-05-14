package no.mesan.ooworkshop.domain;

import static org.junit.Assert.*;

import org.junit.Test;

public class SavingsAccountTest {

    private Long validAccountNumber = 36241604394L;
    private Long invalidAccountNumber = 36241604393L;
    
    private Customer customer = new Customer();

    @Test
    public void newAccountShouldBeCorrect() throws Exception {
        SavingsAccount account = new SavingsAccount(validAccountNumber, 100.0, customer);

        assertEquals(36241604394L, account.getAccountNumber());
        assertEquals(100.0, account.getAmount());
        assertSame(customer, account.getAccountOwner());
    }

    @Test(expected = IllegalArgumentException.class)
    public void newAccountShouldNotAcceptInvalidAccountNumber() throws Exception {
        new SavingsAccount(invalidAccountNumber, 0.0, customer);
    }
}
