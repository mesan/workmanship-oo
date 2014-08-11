package no.mesan.ooworkshop.service;

import no.mesan.ooworkshop.domain.Account;
import no.mesan.ooworkshop.exception.InnsufficientFundsException;

public class TransferService {

    private Account fromAccount;
    private Account toAccount;

    public TransferService(Account fromAccount, Account toAccount) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
    }

    /**
     * Cleaned up :)
     *
     * @param amount
     * @throws InnsufficientFundsException
     */
    public void transfer(Double amount) throws InnsufficientFundsException {
        fromAccount.withdraw(amount);
        toAccount.deposit(amount);
    }
}
