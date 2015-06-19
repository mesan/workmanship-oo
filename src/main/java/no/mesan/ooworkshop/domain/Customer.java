package no.mesan.ooworkshop.domain;

import no.mesan.ooworkshop.util.AccountUtil;

public class Customer {
    private Long socialSecurityNumber;

    private String name;
    private String streetName;
    private Integer streetNumber;
    private Integer postalCode;
    private String town;

    public Customer() {
    }

    public Long getSocialSecurityNumber() {
        return this.socialSecurityNumber;
    }

    public void setSocialSecurityNumber(Long socialSecurityNumber) {
        if (socialSecurityNumber != null && !AccountUtil.gyldigFnr(socialSecurityNumber.toString())) {
            throw new IllegalArgumentException();
        }
        this.socialSecurityNumber = socialSecurityNumber;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStreetName() {
        return this.streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public Integer getStreetNumber() {
        return this.streetNumber;
    }

    public void setStreetNumber(Integer streetNumber) {
        this.streetNumber = streetNumber;
    }

    public Integer getPostalCode() {
        return this.postalCode;
    }

    public void setPostalCode(Integer postalCode) {
        this.postalCode = postalCode;
    }

    public String getTown() {
        return this.town;
    }

    public void setTown(String town) {
        this.town = town;
    }
}
