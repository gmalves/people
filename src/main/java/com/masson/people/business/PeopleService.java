package com.masson.people.business;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.masson.people.business.domain.Address;
import com.masson.people.business.domain.People;
import com.masson.people.business.repository.AddressFinder;
import com.masson.people.business.repository.PeopleProducer;
import com.masson.people.business.repository.PeopleRepository;
import com.masson.people.messaging.PeopleProducerImp;
import com.masson.people.webservice.request.PeopleRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PeopleService {

    @Autowired
    private AddressFinder addressFinder;

    @Autowired
    private PeopleRepository peopleRepository;

    @Autowired
    private PeopleProducer peopleProducer;

    public People create(People people){
        var addressFound = addressFinder.findByZipCode(people.getAddress().getZipCode());
        return createPeople(mapPeople(people, addressFound));
    }

    public People findByDocument(String document){
        return peopleRepository.findByDocument(document);
    }

    private People createPeople(People people) {
        var peopleCreated = peopleRepository.save(people);
        peopleProducer.sendMessage("teste");
        return peopleCreated;
    }

    private People mapPeople(People people, Address address){
        people.getAddress().setState(address.getState());
        people.getAddress().setCity(address.getCity());
        people.getAddress().setDistrict(address.getDistrict());
        people.getAddress().setStreet(address.getStreet());
        people.getAddress().setComplement(address.getComplement());
        return people;
    }
}
