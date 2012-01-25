package no.mesan.ooworkshop.service;

import no.mesan.ooworkshop.domain.Account;
import no.mesan.ooworkshop.domain.CheckingAccount;
import no.mesan.ooworkshop.exception.InnsufficientFundsException;

public class AccountService {

    public void withdraw(Account account, double amount) throws InnsufficientFundsException {
        if (account instanceof CheckingAccount) {
            CheckingAccount checkingAccount = (CheckingAccount) account;

            if (checkingAccount.getAmount() + checkingAccount.getCreditLimit() < amount) {
                throw new InnsufficientFundsException();
            }
        } else {
            if (account.getAmount() < amount) {
                throw new InnsufficientFundsException();
            }
        }
        
        double oldAmount = account.getAmount();
        account.setAmount(oldAmount - amount);
    }
    
    public void deposit(Account account, double amount) {
        double oldAmount = account.getAmount();
        account.setAmount(oldAmount + amount);
    }
}
