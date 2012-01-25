package no.mesan.ooworkshop.domain;

public interface Account {
    long getAccountNumber();
    Customer getAccountOwner();
    Double getAmount();
    void setAmount(Double amount);
}
