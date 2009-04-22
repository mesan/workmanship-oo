package no.mesan.ooworkshop.domain;

import no.mesan.ooworkshop.util.AccountNumberUtil;

public class CheckingAccount implements Account {
	private long accountNumber;
	private Double amount;
	private Double creditLimit;
	private Customer accountOwner;
	
	public CheckingAccount(long accountNumber, Double initialAmount, Double creditLimit, Customer accountOwner) {
		if(!AccountNumberUtil.validAccountNumber(accountNumber)) {
			throw new IllegalArgumentException();
		}
		
		// lalal
		
		this.accountNumber = accountNumber;
		this.amount = initialAmount;
		this.creditLimit = creditLimit;
		this.accountOwner = accountOwner;
	}
	
	public Double getCreditLimit() {
		return creditLimit;
	}
	
	@Override
	public long getAccountNumber() {
		return accountNumber;
	}

	@Override
	public Customer getAccountOwner() {
		return accountOwner;
	}

	@Override
	public Double getAmount() {
		return amount;
	}

	@Override
	public void setAmount(Double amount) {
		this.amount = amount;
	}
}
