package com.masson.people.repository.adapter;

import com.masson.people.business.domain.People;
import com.masson.people.business.repository.PeopleRepository;
import com.masson.people.repository.PeopleRepositoryImp;
import com.masson.people.repository.entity.PeopleEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class PeopleRepositoryAdapter implements PeopleRepository {

    @Autowired
    private PeopleRepositoryImp peopleRepositoryImp;
    @Override
    public People save(People people) {
        var peopleEntity = peopleRepositoryImp.save(PeopleEntity.fromDomain(people));
        return peopleEntity.toDomain();
    }

    @Override
    public People findByDocument(String document) {
        var peopleEntity = peopleRepositoryImp.findByDocument(document);
        if(peopleEntity.isEmpty()) return null;
        return peopleEntity.get().toDomain();
    }
}
