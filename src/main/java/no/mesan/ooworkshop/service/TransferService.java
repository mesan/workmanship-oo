package no.mesan.ooworkshop.service;

import no.mesan.ooworkshop.domain.Account;
import no.mesan.ooworkshop.domain.CheckingAccount;
import no.mesan.ooworkshop.exception.InsufficientFundsException;

public class TransferService {

    private Account fromAccount;
    private Account toAccount;

    public TransferService(Account fromAccount, Account toAccount) {
        this.fromAccount = fromAccount;
        this.toAccount = toAccount;
    }

    /**
     * Ikke pen, med vilje :-)
     *
     * @param amount
     * @throws InsufficientFundsException
     */
    public void transfer(Double amount) throws InsufficientFundsException {
        if (amount < 0) {
            throw new IllegalArgumentException();
        }

        if (fromAccount instanceof CheckingAccount) {
            CheckingAccount checkingAccount = (CheckingAccount) fromAccount;

            if (checkingAccount.getAmount() + checkingAccount.getCreditLimit() < amount) {
                throw new InsufficientFundsException();
            }
        }
        else if (fromAccount.getAmount() < amount) {
            throw new InsufficientFundsException();
        }

        fromAccount.setAmount(fromAccount.getAmount() - amount);
        toAccount.setAmount(toAccount.getAmount() + amount);
    }
}
