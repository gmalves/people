package com.masson.people.repository;

import com.masson.people.repository.entity.PeopleEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PeopleRepositoryMysql extends JpaRepository<PeopleEntity, Long> {

    Optional<PeopleEntity> findByDocument(String document);
}
