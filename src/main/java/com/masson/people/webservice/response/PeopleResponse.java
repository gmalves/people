package com.masson.people.webservice.response;

import com.masson.people.business.domain.People;

import java.time.LocalDate;

public class PeopleResponse {

    private String document;
    private String name;
    private String phone;
    private LocalDate birthdate;
    private AddressResponse address;

    public PeopleResponse fromDomain(People people){
        var response = new PeopleResponse();
        response.name = people.getName();
        response.document = people.getDocument();
        response.phone = people.getPhone();
        response.birthdate = people.getBirthdate();

        response.address = new AddressResponse(
                people.getAddress().getStreet(),
                people.getAddress().getComplement(),
                people.getAddress().getDistrict(),
                people.getAddress().getCity(),
                people.getAddress().getState(),
                people.getAddress().getZipCode(),
                people.getAddress().getNumber());

        return response;
    }

    public String getDocument() {
        return document;
    }

    public String getName() {
        return name;
    }

    public String getPhone() {
        return phone;
    }

    public LocalDate getBirthdate() {
        return birthdate;
    }

    public AddressResponse getAddress() {
        return address;
    }
}
