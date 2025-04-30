package com.desabisc.guide.webflux.router;

import com.desabisc.guide.webflux.handler.PersonHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
public class PersonRouter {

    @Bean
    public RouterFunction<ServerResponse> personRoutes(PersonHandler handler) {
        return route(GET("/api/people"), handler::getAllPeople)
                .andRoute(GET("/api/people/{id}"), handler::getPersonById)
                .andRoute(POST("/api/people"), handler::createPerson)
                .andRoute(PUT("/api/people/{id}"), handler::updatePerson)
                .andRoute(DELETE("/api/people/{id}"), handler::deletePerson)
                .andRoute(GET("/api/people/count"), handler::getPeopleCount);
    }
}