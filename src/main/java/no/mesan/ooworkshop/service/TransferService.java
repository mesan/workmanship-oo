package no.mesan.ooworkshop.service;

import no.mesan.ooworkshop.domain.Account;
import no.mesan.ooworkshop.domain.Money;
import no.mesan.ooworkshop.exception.InsufficientFundsException;

public class TransferService {

    private final Account fromAccount;
    private final Account toAccount;

    public TransferService(Account fromAccount, Account toAccount) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
    }

    /**
     * Cleaned up :)
     *
     * @param amount beløp
     * @throws InsufficientFundsException
     */
    public void transfer(Money amount) throws InsufficientFundsException {
        this.fromAccount.withdraw(amount);
        this.toAccount.deposit(amount);
    }
}
