package com.masson.people.repository.adapter;

import com.masson.people.business.domain.People;
import com.masson.people.business.repository.PeopleRepository;
import com.masson.people.repository.PeopleRepositoryMysql;
import com.masson.people.repository.entity.PeopleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PeopleRepositoryAdapter implements PeopleRepository {

    @Autowired
    private PeopleRepositoryMysql peopleRepositoryMysql;
    @Override
    public People save(People people) {
        var peopleEntity = peopleRepositoryMysql.save(PeopleEntity.fromDomain(people));
        return peopleEntity.toDomain();
    }

    @Override
    public People findByDocument(String document) {
        var peopleEntity = peopleRepositoryMysql.findByDocument(document);
        return peopleEntity.toDomain();
    }
}
