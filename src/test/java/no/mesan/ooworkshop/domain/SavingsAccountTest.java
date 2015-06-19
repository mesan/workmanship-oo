package no.mesan.ooworkshop.domain;

import no.mesan.ooworkshop.exception.InsufficientFundsException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class SavingsAccountTest {

    private final static AccountNumber validAccountNumber = AccountNumber.of(36241604394L);
    private final static Money tohundrelapp = Money.nok(200.0D);
    private final static Money hundreOgFemtiKroner = Money.nok(150.0D);
    private final static Money hundrelapp = Money.nok(100.0D);
    private final static Money femtilapp = Money.nok(50.0D);
    private final static Money konk = Money.nok(0.0D);

    private final Customer customer = new Customer();

    @Test
    public void newAccountShouldBeCorrect() throws Exception {
        SavingsAccount account = new SavingsAccount(validAccountNumber, hundrelapp, this.customer);

        assertEquals("36241604394", account.getAccountNumber().toString());
        assertEquals(hundrelapp, account.getAmount());
        assertSame(this.customer, account.getAccountOwner());
    }

    @Test(expected = IllegalArgumentException.class)
    public void newAccountShouldNotAcceptInvalidAccountNumber() throws Exception {
        new SavingsAccount(AccountNumber.of(36241604393L),konk, this.customer);
    }

    @Test
    public void depositShouldAddAmount() throws Exception {
        SavingsAccount account = new SavingsAccount(validAccountNumber, hundrelapp, new Customer());
        account.deposit(hundrelapp);
        assertEquals(tohundrelapp, account.getAmount());
    }

    @Test
    public void withdrawRemovesMoney() throws Exception {
        SavingsAccount account = new SavingsAccount(validAccountNumber, hundrelapp, new Customer());
        account.withdraw(femtilapp);
        assertEquals(femtilapp, account.getAmount());
    }

    @Test(expected=InsufficientFundsException.class)
    public void withdrawDoesNotOwerdraw() throws Exception {
        SavingsAccount account = new SavingsAccount(validAccountNumber, hundrelapp, new Customer());
        account.withdraw(hundreOgFemtiKroner);
    }

}
