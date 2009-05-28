package no.mesan.ooworkshop.domain;

import no.mesan.ooworkshop.exception.InnsufficientFundsException;
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
        return accountNumber;
    }

    @Override
    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Override
    public Customer getAccountOwner() {
        return accountOwner;
    }
    
    @Override
    public void deposit(double depositAmount) {
        amount += depositAmount;
    }
    
    @Override
    public void withdraw(double withdrawAmount) throws InnsufficientFundsException {
        if (withdrawAmount > amount) {
            throw new InnsufficientFundsException();
        }
        amount -= withdrawAmount;
    }
}
