package com.desabisc.guide.webflux.service;

import com.desabisc.guide.webflux.model.Person;
import com.desabisc.guide.webflux.repository.PersonRepository;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class PersonService {
    private final PersonRepository personRepository;

    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public Flux<Person> getAllPeople() {
        return personRepository.findAll();
    }

    public Mono<Person> getPersonById(Integer id) {
        return personRepository.findById(id)
                .switchIfEmpty(Mono.error(new RuntimeException("Person not found with id: " + id)));
    }

    public Mono<Person> createPerson(Person person) {
        return personRepository.existsById(person.getId())
                .flatMap(exists -> exists 
                    ? Mono.error(new RuntimeException("Person already exists with id: " + person.getId()))
                    : personRepository.save(person)
                );
    }

    public Mono<Person> updatePerson(Integer id, Person person) {
        return personRepository.existsById(id)
                .flatMap(exists -> exists
                    ? personRepository.update(id, person)
                    : Mono.error(new RuntimeException("Person not found with id: " + id))
                );
    }

    public Mono<Void> deletePerson(Integer id) {
        return personRepository.existsById(id)
                .flatMap(exists -> exists
                    ? personRepository.deleteById(id)
                    : Mono.error(new RuntimeException("Person not found with id: " + id))
                );
    }

    public Mono<Long> getPeopleCount() {
        return personRepository.count();
    }
}