package no.mesan.ooworkshop.domain;

import no.mesan.ooworkshop.exception.InsufficientFundsException;

public interface Account {
    AccountNumber getAccountNumber();
    Customer getAccountOwner();
    Money getAmount();

    void deposit(Money depositAmount);
    void withdraw(Money withdrawAmount) throws InsufficientFundsException;
}
