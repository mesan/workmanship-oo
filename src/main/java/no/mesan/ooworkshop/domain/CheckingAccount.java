package no.mesan.ooworkshop.domain;

import no.mesan.ooworkshop.exception.InsufficientFundsException;
import no.mesan.ooworkshop.util.AccountUtil;

public class CheckingAccount implements Account {
    private long accountNumber;
    private Double amount;
    private Double creditLimit;
    private Customer accountOwner;

    public CheckingAccount(long accountNumber,
                           Double initialAmount,
                           Double creditLimit,
                           Customer accountOwner) {
        if (!AccountUtil.validAccountNumber(accountNumber)) {
            throw new IllegalArgumentException();
        }

        this.accountNumber = accountNumber;
        this.amount = initialAmount;
        this.creditLimit = creditLimit;
        this.accountOwner = accountOwner;
    }

    public Double getCreditLimit() {
        return this.creditLimit;
    }

    @Override
    public long getAccountNumber() {
        return this.accountNumber;
    }

    @Override
    public Customer getAccountOwner() {
        return this.accountOwner;
    }

    @Override
    public Double getAmount() {
        return this.amount;
    }

    @Override
    public void deposit(double depositAmount) {
        this.amount += depositAmount;
    }

    @Override
    public void withdraw(double withdrawAmount) throws InsufficientFundsException {
        if (this.amount + this.creditLimit < withdrawAmount) {
            throw new InsufficientFundsException();
        }
        this.amount -= withdrawAmount;
    }
}
