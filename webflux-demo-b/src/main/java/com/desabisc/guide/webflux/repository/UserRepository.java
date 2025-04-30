package com.desabisc.guide.webflux.repository;

import com.desabisc.guide.webflux.model.User;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public class UserRepository {
    // In a real app, this would connect to a reactive database like MongoDB
    public Mono<User> findById(String id) {
        return Mono.just(new User(id, "User " + id));
    }
    
    public Flux<User> findAll() {
        return Flux.just(
            new User("1", "User 1"),
            new User("2", "User 2"),
            new User("3", "User 3")
        );
    }
}