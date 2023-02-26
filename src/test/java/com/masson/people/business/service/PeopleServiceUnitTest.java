package com.masson.people.business.service;

import com.masson.people.business.domain.Address;
import com.masson.people.business.domain.People;
import com.masson.people.business.repository.AddressFinder;
import com.masson.people.business.repository.PeopleProducer;
import com.masson.people.business.repository.PeopleRepository;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class PeopleServiceUnitTest {

    @InjectMocks
    private PeopleService peopleService;

    @Mock
    private AddressFinder addressFinder;

    @Mock
    private PeopleRepository peopleRepository;

    @Mock
    private PeopleProducer peopleProducer;

    @Test
    void createTest(){
        when(addressFinder.findByZipCode("13735450")).thenReturn(new Address());
        when(peopleRepository.save(any())).thenReturn(new People());
        peopleService.create(getPeopleMock());
        verify(addressFinder, times(1)).findByZipCode(any());
        verify(peopleRepository, times(1)).save(any());
        verify(peopleProducer, times(1)).sendMessage(any());
    }

    private People getPeopleMock(){
        var people = new People();
        people.setBirthdate(LocalDate.now());
        people.setPhone("19994407283");
        people.getAddress().setZipCode("13735450");
        return people;
    }
}
