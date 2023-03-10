package com.masson.people.webservice.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.masson.people.business.domain.People;
import jakarta.validation.constraints.*;
import org.hibernate.validator.constraints.Length;

import java.time.LocalDate;

public class PeopleRequest {

    @Length(min = 3, max = 50, message = "O nome deve ter entre 3 e 50 caracteres")
    @NotBlank(message = "O nome deve ser informado")
    private String name;

    @Length(min = 11, max = 11, message = "O documento deve ter 11 caracteres")
    @NotBlank(message = "O documento deve ser informado")
    private String document;

    @Length(min = 8, max = 15, message = "O telefone deve ter entre 8 e 15 caracteres")
    @NotBlank(message = "O documento deve ser informado")
    private String phone;

    @NotNull(message = "A data de nascimento deve ser informada")
    @Past(message = "Data de nascimento futura")
    private LocalDate birthdate;

    @Length(min = 8, max = 8, message = "O CEP deve conter 8 caracteres")
    @NotBlank(message = "O CEP deve ser informado")
    @JsonProperty("zip_code")
    private String zipCode;
    @Max(value = 99999, message = "O número deve ser menor que 99999")
    @NotNull(message = "O Numero deve ser informado")
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
