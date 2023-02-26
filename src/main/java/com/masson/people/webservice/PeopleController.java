package com.masson.people.webservice;

import com.masson.people.business.service.PeopleService;
import com.masson.people.webservice.request.PeopleRequest;
import com.masson.people.webservice.response.PeopleResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class PeopleController {

    @Autowired
    private PeopleService peopleService;

    @PostMapping("/people")
    public ResponseEntity createPeople(@RequestBody PeopleRequest body) {
        var response = peopleService.create(body.toDomain());
        return ResponseEntity.status(HttpStatus.CREATED).body(new PeopleResponse().fromDomain(response));
    }

    @GetMapping("/people/{document}")
    public ResponseEntity createPeople(@PathVariable String document) {
        var response = peopleService.findByDocument(document);
        return ResponseEntity.ok().body(new PeopleResponse().fromDomain(response));
    }
}
