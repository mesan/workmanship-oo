package no.mesan.ooworkshop.domain;

import no.mesan.ooworkshop.exception.InsufficientFundsException;

public abstract class BaseAccount implements Account {

    // TODO: Kontoene støtter nå bare innskudd og uttak i samme valuta som de har fra før...

    private final AccountNumber accountNumber;
    private Money amount;
    private final Customer accountOwner;

    BaseAccount(AccountNumber accountNumber, Customer accountOwner, Money initialAmount) {
        this.accountNumber = accountNumber;
        this.accountOwner = accountOwner;
        this.amount = initialAmount;
    }

    @Override
    public AccountNumber getAccountNumber() {
        return this.accountNumber;
    }

    @Override
    public Money getAmount() {
        return this.amount;
    }

    @Override
    public Customer getAccountOwner() {
        return this.accountOwner;
    }

    @Override
    public void deposit(Money depositAmount) {
        this.amount= this.amount.add(depositAmount);
    }

    @Override
    public void withdraw(Money withdrawAmount) throws InsufficientFundsException {
        if (withdrawAmount.gt(maxWithdrawAmount())) {
            throw new InsufficientFundsException();
        }
        this.amount= this.amount.subtract(withdrawAmount);
    }

    protected abstract Money maxWithdrawAmount();
}
