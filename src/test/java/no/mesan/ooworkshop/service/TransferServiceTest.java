package no.mesan.ooworkshop.service;

import no.mesan.ooworkshop.domain.Account;
import no.mesan.ooworkshop.domain.Customer;
import no.mesan.ooworkshop.domain.SavingsAccount;
import no.mesan.ooworkshop.exception.InsufficientFundsException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class TransferServiceTest {

    @Test
    public void transferWithdrawsAndDeposits() {
        Double initialFromAmount = Double.valueOf(1000);
        Double initialToAmount = Double.valueOf(500);
        Double amount = Double.valueOf(10);

        Account fromAccount = new SavingsAccount(36241604394L, initialFromAmount, new Customer());
        Account toAccount = new SavingsAccount(36241604394L, initialToAmount, new Customer());

        TransferService service = new TransferService(fromAccount, toAccount);

        try {
            service.transfer(amount);
        } catch (InsufficientFundsException e) {
            fail("Should not throw an exception");
        }

        assertEquals(initialFromAmount - amount, fromAccount.getAmount(), 0);
        assertEquals(initialToAmount + amount, toAccount.getAmount(), 0);
    }
}
