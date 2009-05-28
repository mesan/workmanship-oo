package no.mesan.ooworkshop.service;

import static org.junit.Assert.*;
import no.mesan.ooworkshop.domain.Account;
import no.mesan.ooworkshop.domain.Customer;
import no.mesan.ooworkshop.domain.SavingsAccount;
import no.mesan.ooworkshop.exception.InnsufficientFundsException;

import org.junit.Test;

public class TransferServiceTest {

    @Test
    public void transferWithdrawsAndDeposits() throws InnsufficientFundsException {
        double initialFromAmount = 1000.0;
        double initialToAmount = 500.0;
        double amount = 10.0;

        Account fromAccount = new SavingsAccount(36241604394L, initialFromAmount, new Customer());
        Account toAccount = new SavingsAccount(36241604394L, initialToAmount, new Customer());

        TransferService service = new TransferService(fromAccount, toAccount);
        service.transfer(amount);

        assertEquals(initialFromAmount - amount, fromAccount.getAmount());
        assertEquals(initialToAmount + amount, toAccount.getAmount());
    }
    
    @Test(expected=InnsufficientFundsException.class)
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
