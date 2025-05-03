package com.desabisc.guide.java.reactor.cflux;

import java.util.Arrays;
import reactor.core.publisher.Flux;

/**
 * Represents 0 to N items (a stream).
 * Ideal for processing multiple elements (like reading a file or database query).
 */
public class FluxEg {

  public static void main(String[] args) {
    basicUsage();
  }

  static void basicUsage() {
    // Create fluxStrings
    Flux<String> fluxStrings = Flux.just("Hello", "World", "Reactor");
    Flux<String> fluxListFruits = Flux.fromIterable(Arrays.asList("Apple", "Banana", "Cherry"));
    Flux<Integer> fluxIntegers = Flux.range(1, 10);
    Flux<String> fluxEmpty = Flux.empty();

    // Operation: transformation
    Flux<String> fluxStringsUpperCase = fluxStrings.map(String::toUpperCase);
    // Operation: Filtering
    Flux<String> fluxListFruitsFiltered = fluxListFruits.filter(fruit -> fruit.startsWith("A"));

    // subscribe
    fluxStringsUpperCase.subscribe(
        item -> System.out.println("Received: " + item), // onNext
        error -> System.err.println("Error: " + error), // onError
        () -> System.out.println("Done") // onComplete
    );

    // subscribe
    fluxListFruitsFiltered.subscribe(
        item -> System.out.println("Received: " + item), // onNext
        error -> System.err.println("Error: " + error), // onError
        () -> System.out.println("Done") // onComplete
    );

    // subscribe
    fluxIntegers.subscribe(
        item -> System.out.println("Received: " + item), // onNext
        error -> System.err.println("Error: " + error), // onError
        () -> System.out.println("Done") // onComplete
    );
  }
}
