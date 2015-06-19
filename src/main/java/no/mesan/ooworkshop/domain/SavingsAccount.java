package no.mesan.ooworkshop.domain;

public class SavingsAccount extends BaseAccount {

    public SavingsAccount(AccountNumber accountNumber, Money initialAmount, Customer accountOwner) {
        super(accountNumber, accountOwner, initialAmount);
    }

    @Override
    protected Money maxWithdrawAmount() {
        return getAmount();
    }
}
