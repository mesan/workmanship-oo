package no.mesan.ooworkshop.domain;

import no.mesan.ooworkshop.exception.InsufficientFundsException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertSame;

public class CheckingAccountTest {


    private final static AccountNumber validAccountNumber = AccountNumber.of(36241604394L);
    private final static Money tohundrelapp = Money.nok(200.0D);
    private final static Money hundreOgAttiKroner = Money.nok(180.0D);
    private final static Money hundreOgFemtiKroner = Money.nok(150.0D);
    private final static Money hundrelapp = Money.nok(100.0D);
    private final static Money syttiKroner = Money.nok(70.0D);
    private final static Money femtilapp = Money.nok(50.0D);
    private final static Money konk = Money.nok(0.0D);

    private final Customer customer = new Customer();

    @Test
    public void newAccountShouldBeCorrect() throws Exception {
        CheckingAccount account = new CheckingAccount(validAccountNumber, hundrelapp, femtilapp,
                                                      this.customer);

        assertEquals("36241604394", account.getAccountNumber().toString());
        assertEquals(100.0, account.getAmount().normalized().doubleValue(), 0);
        assertEquals(50.0, account.getCreditLimit().normalized().doubleValue(), 0);
        assertSame(this.customer, account.getAccountOwner());
    }

    @Test(expected = IllegalArgumentException.class)
    public void newAccountShouldNotAcceptInvalidAccountNumber() throws Exception {
        new CheckingAccount(AccountNumber.of(36241604393L), konk, hundrelapp, this.customer);
    }

    @Test
    public void depositShouldAddAmount() throws Exception {
        CheckingAccount account = new CheckingAccount(validAccountNumber, hundrelapp, femtilapp, new Customer());
        account.deposit(hundrelapp);

        assertEquals(tohundrelapp, account.getAmount());
    }

    @Test
    public void withdrawRemovesMoney() throws Exception {
        CheckingAccount account = new CheckingAccount(validAccountNumber, hundrelapp, femtilapp, new Customer());
        account.withdraw(femtilapp);

        assertEquals(femtilapp, account.getAmount());
    }

    @Test
    public void withdrawAllowsMoreThanCurrentAmountForCheckingAccount() throws Exception {
        CheckingAccount account = new CheckingAccount(validAccountNumber, hundrelapp, syttiKroner, new Customer());
        account.withdraw(hundreOgFemtiKroner);
        assertEquals(-50.0D, account.getAmount().normalized().doubleValue(), 0);
    }

    @Test(expected=InsufficientFundsException.class)
    public void withdrawDoesNotOwerdrawAmountPlusCredit() throws Exception {
        CheckingAccount account = new CheckingAccount(validAccountNumber, hundrelapp, syttiKroner, new Customer());
        account.withdraw(hundreOgAttiKroner);
    }
}
