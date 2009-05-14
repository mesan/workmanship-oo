package no.mesan.ooworkshop.service;

import no.mesan.ooworkshop.domain.Account;
import no.mesan.ooworkshop.domain.CheckingAccount;
import no.mesan.ooworkshop.exception.InnsufficientFundsException;

public class TransferService {
	public static final double FEE = 5;  
	
	private Account fromAccount;
	private Account toAccount;
	
	public TransferService(Account fromAccount, Account toAccount) {
		this.fromAccount = fromAccount;
		this.toAccount = toAccount;
	}
	
	/**
	 * Ikke pen, med vilje :-)
	 * 
	 * @param amount
	 * @throws InnsufficientFundsException
	 */
	public void transfer(Double amount) throws InnsufficientFundsException {
		if(amount < 0) {
			throw new IllegalArgumentException();
		}
		
		Double fromAmount = addTransferFeeToAmount(amount);
		Double toAmount = amount;
				
		if(fromAccount instanceof CheckingAccount) {
			CheckingAccount checkingAccount = (CheckingAccount)fromAccount;
			
			if(checkingAccount.getAmount() + checkingAccount.getCreditLimit() < fromAmount) {
				throw new InnsufficientFundsException();
			}
		} else {
			if(fromAccount.getAmount() < fromAmount) {
				throw new InnsufficientFundsException();
			}
		}
		
		fromAccount.setAmount(fromAccount.getAmount() - fromAmount);
		toAccount.setAmount(toAccount.getAmount() + toAmount);
	}
	
	private Double addTransferFeeToAmount(Double amount) {
		return amount + FEE;
	}
}
