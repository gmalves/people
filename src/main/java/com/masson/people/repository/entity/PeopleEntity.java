package com.masson.people.repository.entity;

import com.masson.people.business.domain.Address;
import com.masson.people.business.domain.People;
import jakarta.persistence.*;

import java.time.LocalDate;


@Entity
@Table(name = "tb_people")
public class PeopleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String document;
    private String name;
    private String phone;
    private LocalDate birthdate;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private AddressEntity address;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public AddressEntity getAddress() {
        return address;
    }

    public void setAddress(AddressEntity address) {
        this.address = address;
    }

    public static PeopleEntity fromDomain(People people){
        var addressEntity = new AddressEntity();
        var address = people.getAddress();
        addressEntity.setId(address.getId());
        addressEntity.setZipCode(address.getZipCode());
        addressEntity.setState(address.getState());
        addressEntity.setCity(address.getCity());
        addressEntity.setDistrict(address.getDistrict());
        addressEntity.setStreet(address.getStreet());
        addressEntity.setNumber(address.getNumber());
        addressEntity.setComplement(address.getComplement());
        var peopleEntity = new PeopleEntity();
        peopleEntity.setAddress(addressEntity);
        peopleEntity.setName(people.getName());
        peopleEntity.setPhone(people.getPhone());
        peopleEntity.setDocument(people.getDocument());
        peopleEntity.setBirthdate(people.getBirthdate());
        peopleEntity.setId(people.getId());
        return peopleEntity;
    }

    public People toDomain(){
        var address = new Address();
        var addressEntity = this.getAddress();
        address.setId(addressEntity.getId());
        address.setZipCode(addressEntity.getZipCode());
        address.setState(addressEntity.getState());
        address.setCity(addressEntity.getCity());
        address.setDistrict(addressEntity.getDistrict());
        address.setStreet(addressEntity.getStreet());
        address.setNumber(addressEntity.getNumber());
        address.setComplement(addressEntity.getComplement());
        var people = new People();
        people.setAddress(address);
        people.setName(this.getName());
        people.setPhone(this.getPhone());
        people.setDocument(this.getDocument());
        people.setBirthdate(this.getBirthdate());
        people.setId(this.getId());
        return people;
    }
}
