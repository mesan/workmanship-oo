package no.mesan.ooworkshop.domain;

import no.mesan.ooworkshop.util.AccountNumberUtil;

public class SavingsAccount implements Account {
	private long accountNumber;
	private Double amount;
	private Customer accountOwner;
	
	public SavingsAccount(long accountNumber, Double initialAmount, Customer accountOwner) {
		if(!AccountNumberUtil.validAccountNumber(accountNumber)) {
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

	@Override
	public void setAmount(Double amount) {
		this.amount = amount;
	}

	@Override
	public Customer getAccountOwner() {
		return accountOwner;
	}
}
