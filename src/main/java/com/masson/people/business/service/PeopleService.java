package com.masson.people.business.service;

import com.masson.people.business.domain.Address;
import com.masson.people.business.domain.People;
import com.masson.people.business.exception.PeopleNotFoundException;
import com.masson.people.business.exception.RegisteredPeopleException;
import com.masson.people.business.repository.AddressFinder;
import com.masson.people.business.repository.PeopleProducer;
import com.masson.people.business.repository.PeopleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PeopleService {

    private static Logger logger = LoggerFactory.getLogger(PeopleService.class);
    @Autowired
    private AddressFinder addressFinder;

    @Autowired
    private PeopleRepository peopleRepository;

    @Autowired
    private PeopleProducer peopleProducer;

    public People create(People people){
        logger.info("Starting create people with document " + people.getDocument());
        var peopleFound = peopleRepository.findByDocument(people.getDocument());
        if(peopleFound != null ) throw new RegisteredPeopleException();
        var addressFound = addressFinder.findByZipCode(people.getAddress().getZipCode());
        return createPeople(mapPeople(people, addressFound));
    }

    public People findByDocument(String document){
        logger.info("Find people with document " + document);
        var peopleFound = peopleRepository.findByDocument(document);
        if(peopleFound == null) throw new PeopleNotFoundException();
        return peopleFound;
    }

    private People createPeople(People people) {
        logger.info("Saving people with document " + people.getDocument());
        var peopleCreated = peopleRepository.save(people);
/*
*
*       NÃO FUNCIONOU QUANDO SUBIDO VIA DOCKER, SE SUBIR LOCAL FUNCIONA
*       POR ALGUM MOTIVO NÃO CONSEGUE ESTABELE CONEXÃO
*
*/
//        peopleProducer.sendMessage(peopleCreated.getDocument());
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
