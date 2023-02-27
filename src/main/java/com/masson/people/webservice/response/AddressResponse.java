package com.masson.people.webservice.response;

import com.fasterxml.jackson.annotation.JsonProperty;

public class AddressResponse {
    private String street;
    private String complement;
    private String district;
    private String city;
    private String state;
    @JsonProperty("zip_code")
    private String zipCode;
    private Long number;

    public AddressResponse(String street, String complement, String district, String city, String state, String zipCode, Long number) {
        this.street = street;
        this.complement = complement;
        this.district = district;
        this.city = city;
        this.state = state;
        this.zipCode = zipCode;
        this.number = number;
    }

    public String getStreet() {
        return street;
    }

    public String getComplement() {
        return complement;
    }

    public String getDistrict() {
        return district;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    public String getZipCode() {
        return zipCode;
    }

    public Long getNumber() {
        return number;
    }
}
