package com.masson.people.webservice.request;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.masson.people.business.domain.People;

import java.time.LocalDate;

public class PeopleRequest {

    private String name;
    private String document;
    private String phone;
    private LocalDate birthdate;
    @JsonProperty("zip_code")
    private String zipCode;
    @JsonProperty("address_number")
    private Long addressNumber;

    public PeopleRequest() { }

    public PeopleRequest(String name, String document, String phone, LocalDate birthdate, String zipCode, Long addressNumber) {
        this.name = name;
        this.document = document;
        this.phone = phone;
        this.birthdate = birthdate;
        this.zipCode = zipCode;
        this.addressNumber = addressNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getZipCode() {
        return zipCode;
    }

    public void setZipCode(String zipCode) {
        this.zipCode = zipCode;
    }

    public Long getAddressNumber() {
        return addressNumber;
    }

    public void setAddressNumber(Long addressNumber) {
        this.addressNumber = addressNumber;
    }

    public People toDomain(){
        var people = new People();
        people.setName(this.name);
        people.setDocument(this.document);
        people.setPhone(this.phone);
        people.setBirthdate(this.birthdate);
        people.getAddress().setZipCode(this.zipCode);
        people.getAddress().setNumber(this.addressNumber);
        return people;
    }
}
