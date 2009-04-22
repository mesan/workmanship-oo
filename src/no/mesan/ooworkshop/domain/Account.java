package no.mesan.ooworkshop.domain;

public interface Account {
	public long getAccountNumber();
	public Customer getAccountOwner();
	public Double getAmount();
	public void setAmount(Double amount);
	
}
