package com.desabisc.guide.webflux.controller;

import com.desabisc.guide.webflux.model.Person;
import com.desabisc.guide.webflux.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/people")
public class PersonController {
    private final PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public Flux<Person> getAllPeople() {
        return personService.getAllPeople();
    }

    @GetMapping("/{id}")
    public Mono<Person> getPersonById(@PathVariable Integer id) {
        return personService.getPersonById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<Person> createPerson(@RequestBody Person person) {
        return personService.createPerson(person);
    }

    @PutMapping("/{id}")
    public Mono<Person> updatePerson(@PathVariable Integer id, @RequestBody Person person) {
        return personService.updatePerson(id, person);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deletePerson(@PathVariable Integer id) {
        return personService.deletePerson(id);
    }

    @GetMapping("/count")
    public Mono<Long> getPeopleCount() {
        return personService.getPeopleCount();
    }
}