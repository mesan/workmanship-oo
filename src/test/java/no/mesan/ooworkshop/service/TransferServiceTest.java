package no.mesan.ooworkshop.service;

import no.mesan.ooworkshop.domain.Account;
import no.mesan.ooworkshop.domain.AccountNumber;
import no.mesan.ooworkshop.domain.Customer;
import no.mesan.ooworkshop.domain.Money;
import no.mesan.ooworkshop.domain.SavingsAccount;
import no.mesan.ooworkshop.exception.InsufficientFundsException;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class TransferServiceTest {

    private final static AccountNumber validAccountNumber = AccountNumber.of(36241604394L);

    @Test
    public void transferWithdrawsAndDeposits() throws InsufficientFundsException {
        Money initialFromAmount = Money.nok(1000.0D);
        Money initialToAmount = Money.nok(500.0D);
        Money amount = Money.nok(10.0D);

        Account fromAccount = new SavingsAccount(validAccountNumber, initialFromAmount, new Customer());
        Account toAccount = new SavingsAccount(validAccountNumber, initialToAmount, new Customer());

        TransferService service = new TransferService(fromAccount, toAccount);
        service.transfer(amount);

        assertEquals(initialFromAmount.normalized().subtract(amount.normalized()), fromAccount.getAmount().normalized());
        assertEquals(initialToAmount.normalized().add(amount.normalized()), toAccount.getAmount().normalized());
    }

    @Test(expected=InsufficientFundsException.class)
    public void transferShouldFailForToLargeWithdraw() throws Exception {
        Money initialFromAmount = Money.nok(1000.0D);
        Money initialToAmount = Money.nok(500.0D);
        Money amount = Money.nok(1001.0D);

        Account fromAccount = new SavingsAccount(validAccountNumber, initialFromAmount, new Customer());
        Account toAccount = new SavingsAccount(validAccountNumber, initialToAmount, new Customer());

        TransferService service = new TransferService(fromAccount, toAccount);
        service.transfer(amount);
    }
}
