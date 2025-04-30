package com.desabisc.guide.webflux.repository;

import com.desabisc.guide.webflux.model.Person;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class PersonRepository {
    private final ConcurrentHashMap<Integer, Person> personMap = generateRandomPeople();

    public PersonRepository() {
        // The map is already initialized and populated
    }

    private ConcurrentHashMap<Integer, Person> generateRandomPeople() {
        ConcurrentHashMap<Integer, Person> people = new ConcurrentHashMap<>();
        
        // Add people directly to the thread-safe map
        people.put(1, new Person(1, "John", 23, 72.5, 'M', false));
        people.put(2, new Person(2, "Emma", 34, 65.0, 'F', true));
        people.put(3, new Person(3, "Liam", 28, 80.3, 'M', false));
        people.put(4, new Person(4, "Olivia", 45, 59.7, 'F', true));
        people.put(5, new Person(5, "Noah", 31, 85.1, 'M', true));
        people.put(6, new Person(6, "Ava", 26, 54.2, 'F', false));
        people.put(7, new Person(7, "Sophia", 37, 62.8, 'F', true));
        people.put(8, new Person(8, "James", 29, 77.0, 'M', false));
        people.put(9, new Person(9, "Isabella", 41, 60.5, 'F', true));
        people.put(10, new Person(10, "Mason", 22, 70.4, 'M', false));
        
        return people;
    }

    public Flux<Person> findAll() {
        return Flux.fromIterable(personMap.values());
    }

    public Mono<Person> findById(Integer id) {
        return Mono.justOrEmpty(personMap.get(id));
    }

    public Mono<Boolean> existsById(Integer id) {
        return Mono.just(personMap.containsKey(id));
    }

    public Mono<Person> save(Person person) {
        return Mono.fromCallable(() -> {
            personMap.put(person.getId(), person);
            return person;
        });
    }

    public Mono<Person> update(Integer id, Person updatedPerson) {
        return Mono.fromCallable(() -> {
            updatedPerson.setId(id);
            personMap.put(id, updatedPerson);
            return updatedPerson;
        });
    }

    public Mono<Void> deleteById(Integer id) {
        return Mono.fromRunnable(() -> personMap.remove(id));
    }

    public Mono<Long> count() {
        return Mono.just((long) personMap.size());
    }
}