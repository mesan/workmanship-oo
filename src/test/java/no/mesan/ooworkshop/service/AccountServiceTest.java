package no.mesan.ooworkshop.service;

import no.mesan.ooworkshop.domain.Account;
import no.mesan.ooworkshop.domain.CheckingAccount;
import no.mesan.ooworkshop.domain.Customer;
import no.mesan.ooworkshop.domain.SavingsAccount;
import no.mesan.ooworkshop.exception.InsufficientFundsException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AccountServiceTest {
    AccountService service = new AccountService();

    @Test
    public void depositAddsMoney() throws Exception {
        Account account = new SavingsAccount(36241604394L, 100.0, new Customer());
        service.deposit(account, 100.0);

        assertEquals(200.0, account.getAmount(), 0);
    }

    @Test
    public void withdrawRemovesMoney() throws Exception {
        Account account = new SavingsAccount(36241604394L, 100.0, new Customer());
        service.withdraw(account, 50.0);

        assertEquals(50.0, account.getAmount(), 0);
    }

    @Test(expected=InsufficientFundsException.class)
    public void withdrawDoesNotOwerdraw() throws Exception {
        Account account = new SavingsAccount(36241604394L, 100.0, new Customer());
        service.withdraw(account, 150.0);
    }

    @Test
    public void withdrawAllowsMoreThanCurrentAmountForCheckingAccount() throws Exception {
        Account account = new CheckingAccount(36241604394L, 100.0, 70.0, new Customer());
        service.withdraw(account, 150.0);
        assertEquals(-50.0, account.getAmount(), 0);
    }

    @Test(expected=InsufficientFundsException.class)
    public void withdrawDoesNotOwerdrawAmountPlusCredit() throws Exception {
        Account account = new CheckingAccount(36241604394L, 100.0, 70.0, new Customer());
        service.withdraw(account, 180.0);
    }
}
