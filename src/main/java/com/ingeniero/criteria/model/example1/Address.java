package com.ingeniero.criteria.model.example1;


import jakarta.persistence.Embeddable;

@Embeddable
public class Address {


    private String street;
    private String city;
    private String country;

    public Address(String street, String city, String country) {

        this.street = street;
        this.city = city;
        this.country = country;
    }

    public Address() {
    }

    @Override
    public String toString() {
        return "Address{" +
                "street='" + street + '\'' +
                ", city='" + city + '\'' +
                ", country='" + country + '\'' +
                '}';
    }



    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }
}
