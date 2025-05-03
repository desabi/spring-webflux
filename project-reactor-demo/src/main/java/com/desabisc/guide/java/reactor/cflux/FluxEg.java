package com.desabisc.guide.java.reactor.cflux;

import java.util.Arrays;
import reactor.core.publisher.Flux;

/**
 * Represents 0 to N items (a stream).
 * Ideal for processing multiple elements (like reading a file or database query).
 */
public class FluxEg {

  public static void main(String[] args) {
    //basicUsage();
    //chainedOperationsA();
    chainedOperationsB();
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

  static void chainedOperationsA() {
    Flux<String> fluxListFruits = Flux.just("Apple", "Avocado", "Banana", "Blueberry",
        "Cherry", "Coconut", "Fig", "Grape", "Kiwi", "Lemon", "Mango", "Orange", "Pear", "Tangerine",
        "Watermelon");

    fluxListFruits
        .map(String::toUpperCase)
        .filter(fruit -> fruit.length() > 5)
        .map(fruit -> fruit + " is delicious")
        .doOnNext(element -> System.out.println("Processing: " + element))
        .subscribe(
            result -> System.out.println("Result: " + result), // onNext: This consumer receives each emitted value
            error -> System.out.println("Error occurred: " + error.getMessage()), // onError: This consumer handles any errors
            () -> System.out.println("Processing complete!")
        );
  }

  static void chainedOperationsB() {
    Flux<String> fluxListFruits = Flux.just("Apple", "Avocado", "Banana", "Blueberry",
        "Cherry", "Coconut", "Fig", "Grape", "Kiwi", "Lemon", "Mango", "Orange", "Pear", "Tangerine",
        "Watermelon");

    fluxListFruits
        .filter(fruit -> fruit.length() > 5)
        .map(String::toUpperCase)
        .sort()
        .distinct()
        //.take(5)
        .flatMap(fruit -> Flux.just(fruit.split(""))) // 6. Split into individual letters
        .buffer(3) // 7. Group letters into lists of 3
        .subscribe(
            letters -> System.out.println("Letter group: " + letters),
            error -> System.err.println("Error: " + error),
            () -> System.out.println("Processing complete!")
        );

    fluxListFruits
        .filter(fruit -> fruit.contains("a"))            // Filter fruits containing 'a'
        .map(fruit -> fruit.replace("a", "*"))          // Replace 'a' with *
        .collectList()                                   // Collect into a List
        .subscribe(
            list -> System.out.println("Modified fruits: " + list),
            error -> System.err.println("Error: " + error),
            () -> System.out.println("Collection complete!")
        );
  }
}
