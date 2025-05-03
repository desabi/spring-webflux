package com.desabisc.guide.java.reactor.acore;

import java.util.concurrent.Flow.Processor;
import java.util.concurrent.Flow.Publisher;
import java.util.concurrent.Flow.Subscriber;
import java.util.concurrent.Flow.Subscription;

/**
 * Reactive Streams is a standard for asynchronous stream processing with non-blocking back pressure,
 * introduced in Java 9 as part of the java.util.concurrent.Flow API. It provides a common interface for reactive programming libraries to ensure interoperability.
 */
public class FlowInterfaces {
  public static void main(String[] args) {

    // Produces items that can be consumed by subscribers
    // Accepts subscriber registrations
    Publisher publisher; // void subscribe(Subscriber<? super T> subscriber);

    // Receives items from a publisher
    // Methods are called in strict sequence: onSubscribe → onNext* → (onError | onComplete)
    Subscriber subscriber;
    /*
    void onSubscribe(Subscription subscription);
    void onNext(T item);
    void onError(Throwable throwable);
    void onComplete();
    */

    // Controls the flow between publisher and subscriber
    // request(n) - asks for n more items (back pressure)
    // cancel() - stops receiving items
    Subscription subscription;
    /*
    void request(long n);
    void cancel();
     */

    // extends both Publisher and Subscriber
    // Acts as both subscriber and publisher
    // Used for transforming data in a stream
    Processor processor;
  }
}
