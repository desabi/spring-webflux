package com.desabisc.guide.webflux.service;

import com.desabisc.guide.webflux.model.User;
import com.desabisc.guide.webflux.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class UserService {
    private final UserRepository userRepository;
    
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    public Mono<User> getUserById(String id) {
        return userRepository.findById(id);
    }
    
    public Flux<User> getAllUsers() {
        return userRepository.findAll();
    }
}