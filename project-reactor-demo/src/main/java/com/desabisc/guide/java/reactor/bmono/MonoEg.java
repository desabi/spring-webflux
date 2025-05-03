package com.desabisc.guide.java.reactor.bmono;

import reactor.core.publisher.Mono;

/**
 * Represents 0 or 1 item.
 * Use it when you expect a single result or none (like a DB lookup or HTTP call).
 */
public class MonoEg {

  public static void main(String[] args) {
    basicUsage();
  }

  static void basicUsage() {
    // Creating a Mono
    Mono<String> mono = Mono.just("Hello");
    // Empty sequences
    Mono<String> emptyMono = Mono.empty();

    // Apply an operation (transform to uppercase)
    Mono<String> upperCaseMono = mono.map(String::toUpperCase);

    // Subscribe to execute the Mono and process the result
    upperCaseMono.subscribe(
        // onNext: This consumer receives the emitted value
        value -> System.out.println("Received: " + value),
        // onError: This consumer handles any errors
        error -> System.err.println("Error occurred: " + error.getMessage()),
        // onComplete: This runnable is called when the sequence completes
        () -> System.out.println("Processing completed!")
    );

    // Alternative shorter version
    mono.map(String::toUpperCase)
        .subscribe(result -> System.out.println("Result: " + result));
  }
}
