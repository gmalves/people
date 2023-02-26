package com.masson.people.business.repository;

import com.masson.people.business.domain.People;

public interface PeopleRepository {
    People save(People people);
    People findByDocument(String document);
}
