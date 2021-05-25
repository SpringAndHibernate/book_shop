package by.bsuir.book_shop.entity;

import javax.persistence.Embeddable;

@Embeddable
public class Address {

    private String city;
    private String street;
    private String building;
    private String postalCode;

    public Address() {
    }

    public Address(String city, String street, String building, String postalCode) {
        this.city = city;
        this.street = street;
        this.building = building;
        this.postalCode = postalCode;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getBuilding() {
        return building;
    }

    public void setBuilding(String building) {
        this.building = building;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    @Override
    public String toString() {
        return "г. " + city + "," + street + "," + building + "," + "индекс " + postalCode;
    }
}
