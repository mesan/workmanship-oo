package no.mesan.ooworkshop.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

import org.junit.Test;

public class CheckingAccountTest {

    private Long validAccountNumber = 36241604394L;
    private Long invalidAccountNumber = 36241604393L;
    
    private Customer customer = new Customer();
    
    @Test
    public void newAccountShouldBeCorrect() throws Exception {
        CheckingAccount account = new CheckingAccount(validAccountNumber, 100.0, 50.0 ,customer);

        assertEquals(36241604394L, account.getAccountNumber());
        assertEquals(100.0, account.getAmount(), 0);
        assertEquals(50.0, account.getCreditLimit(), 0);
        assertSame(customer, account.getAccountOwner());
    }

    @Test(expected = IllegalArgumentException.class)
    public void newAccountShouldNotAcceptInvalidAccountNumber() throws Exception {
        new CheckingAccount(invalidAccountNumber, 0.0, 100.0, customer);
    }
}
