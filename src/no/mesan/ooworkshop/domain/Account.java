package no.mesan.ooworkshop.domain;

import no.mesan.ooworkshop.exception.InnsufficientFundsException;

public interface Account {
    long getAccountNumber();
    Customer getAccountOwner();
    Double getAmount();

    void deposit(double depositAmount);
    void withdraw(double withdrawAmount) throws InnsufficientFundsException;
}
