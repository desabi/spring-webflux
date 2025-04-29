package com.desabisc.guide.webflux.client;

import com.desabisc.guide.webflux.model.Greeting;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

/**
 * We use a WebClient-based implementation to consume our RESTful service:
 */
@Component
public class GreetingClient {
  private final WebClient webClient;

  public GreetingClient(WebClient.Builder builder) {
    this.webClient = builder.baseUrl("http://localhost:8080").build();
  }

  public Mono<String> getMessage() {
    return this.webClient
        .get()
        .uri("/hello")
        .accept(MediaType.APPLICATION_JSON)
        .retrieve()
        .bodyToMono(Greeting.class)
        .map(Greeting::getMessage);
  }
}
