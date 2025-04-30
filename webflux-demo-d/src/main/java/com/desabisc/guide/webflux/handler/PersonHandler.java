package com.desabisc.guide.webflux.handler;

import com.desabisc.guide.webflux.model.Person;
import com.desabisc.guide.webflux.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

@Component
public class PersonHandler {
    private final PersonService personService;

    public PersonHandler(PersonService personService) {
        this.personService = personService;
    }

    public Mono<ServerResponse> getAllPeople(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(personService.getAllPeople(), Person.class);
    }

    public Mono<ServerResponse> getPersonById(ServerRequest request) {
        Integer id = Integer.parseInt(request.pathVariable("id"));
        return personService.getPersonById(id)
                .flatMap(person -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(person))
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> createPerson(ServerRequest request) {
        return request.bodyToMono(Person.class)
                .flatMap(personService::createPerson)
                .flatMap(person -> ServerResponse
                        .status(HttpStatus.CREATED)
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(person))
                .onErrorResume(e -> ServerResponse.badRequest()
                        .bodyValue(e.getMessage()));
    }

    public Mono<ServerResponse> updatePerson(ServerRequest request) {
        Integer id = Integer.parseInt(request.pathVariable("id"));
        return request.bodyToMono(Person.class)
                .flatMap(person -> personService.updatePerson(id, person))
                .flatMap(person -> ServerResponse.ok()
                        .contentType(MediaType.APPLICATION_JSON)
                        .bodyValue(person))
                .switchIfEmpty(ServerResponse.notFound().build())
                .onErrorResume(e -> ServerResponse.badRequest()
                        .bodyValue(e.getMessage()));
    }

    public Mono<ServerResponse> deletePerson(ServerRequest request) {
        Integer id = Integer.parseInt(request.pathVariable("id"));
        return personService.deletePerson(id)
                .then(ServerResponse.noContent().build())
                .switchIfEmpty(ServerResponse.notFound().build());
    }

    public Mono<ServerResponse> getPeopleCount(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(personService.getPeopleCount(), Long.class);
    }
}