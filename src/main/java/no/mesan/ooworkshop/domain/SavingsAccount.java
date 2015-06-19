package no.mesan.ooworkshop.domain;

import no.mesan.ooworkshop.exception.InsufficientFundsException;
import no.mesan.ooworkshop.util.AccountUtil;

public class SavingsAccount implements Account {
    private long accountNumber;
    private Double amount;
    private Customer accountOwner;

    public SavingsAccount(long accountNumber, Double initialAmount, Customer accountOwner) {
        if (!AccountUtil.validAccountNumber(accountNumber)) {
            throw new IllegalArgumentException();
        }

        this.accountNumber = accountNumber;
        this.amount = initialAmount;
        this.accountOwner = accountOwner;
    }

    @Override
    public long getAccountNumber() {
        return this.accountNumber;
    }

    @Override
    public Double getAmount() {
        return this.amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public Customer getAccountOwner() {
        return this.accountOwner;
    }

    @Override
    public void deposit(double depositAmount) {
        this.amount += depositAmount;
    }

    @Override
    public void withdraw(double withdrawAmount) throws InsufficientFundsException {
        if (withdrawAmount > this.amount) {
            throw new InsufficientFundsException();
        }
        this.amount -= withdrawAmount;
    }
}
