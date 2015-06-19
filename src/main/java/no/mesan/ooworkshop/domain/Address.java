package no.mesan.ooworkshop.domain;

public class Address {
    private final String streetName;
    private final Integer streetNumber;
    private final Integer postalCode;
    private final String town;

    public Address(final String streetName, final Integer streetNumber, final Integer postalCode, final String town) {
        this.streetName = streetName;
        this.streetNumber = streetNumber;
        this.postalCode = postalCode;
        this.town = town;
    }

    public String getStreetName() {
        return this.streetName;
    }

    public Integer getStreetNumber() {
        return this.streetNumber;
    }

    public Integer getPostalCode() {
        return this.postalCode;
    }

    public String getTown() {
        return this.town;
    }
}