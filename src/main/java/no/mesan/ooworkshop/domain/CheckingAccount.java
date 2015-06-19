package no.mesan.ooworkshop.domain;

public class CheckingAccount extends BaseAccount {
    private final Money creditLimit;

    public CheckingAccount(AccountNumber accountNumber,
                           Money initialAmount,
                           Money creditLimit,
                           Customer accountOwner) {
        super(accountNumber, accountOwner, initialAmount);
        this.creditLimit = creditLimit;
    }

    public Money getCreditLimit() {
        return this.creditLimit;
    }

    @Override
    protected Money maxWithdrawAmount() {
        return getAmount().add(getCreditLimit());
    }
}
