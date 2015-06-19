package ooworkshop.service;

import no.mesan.ooworkshop.domain.Account;
import no.mesan.ooworkshop.domain.Customer;
import no.mesan.ooworkshop.domain.SavingsAccount;
import no.mesan.ooworkshop.exception.InsufficientFundsException;
import no.mesan.ooworkshop.service.TransferService;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TransferServiceTest {

    @Test
    public void transferWithdrawsAndDeposits() throws InsufficientFundsException {
        double initialFromAmount = 1000.0;
        double initialToAmount = 500.0;
        double amount = 10.0;

        Account fromAccount = new SavingsAccount(36241604394L, initialFromAmount, new Customer());
        Account toAccount = new SavingsAccount(36241604394L, initialToAmount, new Customer());

        TransferService service = new TransferService(fromAccount, toAccount);
        service.transfer(amount);

        assertEquals(initialFromAmount - amount, fromAccount.getAmount(), 0);
        assertEquals(initialToAmount + amount, toAccount.getAmount(), 0);
    }

    @Test(expected=InsufficientFundsException.class)
    public void transferShouldFailForToLargeWithdraw() throws Exception {
        double initialFromAmount = 1000.0;
        double initialToAmount = 500.0;
        double amount = 1001.0;

        Account fromAccount = new SavingsAccount(36241604394L, initialFromAmount, new Customer());
        Account toAccount = new SavingsAccount(36241604394L, initialToAmount, new Customer());

        TransferService service = new TransferService(fromAccount, toAccount);
        service.transfer(amount);
    }
}
