package no.mesan.ooworkshop.domain;

public class Customer {

    private SocialSecurityNumber socialSecurityNumber;

    private String name;

    private Address address;

    public Customer() {
        super();
    }

    public Address getAddress() {
        return this.address;
    }

    public void setAddress(final Address address) {
        this.address = address;
    }

    public SocialSecurityNumber getSocialSecurityNumber() {
        return this.socialSecurityNumber;
    }

    public void setSocialSecurityNumber(SocialSecurityNumber socialSecurityNumber) {
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
