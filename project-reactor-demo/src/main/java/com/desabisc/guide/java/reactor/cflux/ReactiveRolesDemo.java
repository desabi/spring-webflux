package com.desabisc.guide.java.reactor.cflux;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

/**
 * There is the short version using lambdas.
 */
public class ReactiveRolesDemo {
    public static void main(String[] args) {
        // =====================================================================
        // 1. THE PUBLISHER
        // =====================================================================
        // This is your data source. It's a "cold" publisher: it won't emit anything
        // until someone subscribes. It knows how to produce "Alpha", "Bravo", "Charlie"
        // when demand is requested.
        Publisher<String> publisher = Flux.just("Alpha", "Bravo", "Charlie");

        // =====================================================================
        // 2. THE SUBSCRIBER (implemented by us)
        // =====================================================================
        // The Subscriber is the consumer. We define how it reacts to each signal.
        // We must implement the four methods of the Subscriber interface:
        //   onSubscribe, onNext, onError, onComplete
        Subscriber<String> subscriber = new Subscriber<String>() {

            // This field will hold the Subscription token (the "remote control").
            // It's set inside onSubscribe().
            private Subscription subscription;

            // ---------- onSubscribe ----------
            // This is the FIRST signal the subscriber receives.
            // The Publisher calls this method and hands over the Subscription
            // (the link that allows us to request data or cancel).
            // This method MUST be called exactly once.
            @Override
            public void onSubscribe(Subscription s) {
                System.out.println("onSubscribe called. I got the Subscription object: " + s);
                // Save the subscription to use it later (for request/cancel).
                this.subscription = s;
                // Now we signal how many items we are ready to consume.
                // Without this call, NO DATA will flow!
                // We request exactly 2 items to start.
                System.out.println("Requesting 2 items upfront...");
                subscription.request(2);
            }

            // ---------- onNext ----------
            // The Publisher calls this for each requested item.
            // It can be called 0..N times, but never concurrently.
            @Override
            public void onNext(String item) {
                System.out.println("onNext received: " + item);
                // After processing an item, we can request more.
                // This demonstrates incremental demand.
                System.out.println("  (now requesting 1 more item)");
                subscription.request(1); // ask for one additional item
            }

            // ---------- onError ----------
            // Terminal event: the stream failed. No more signals will arrive.
            @Override
            public void onError(Throwable throwable) {
                System.err.println("onError: " + throwable.getMessage());
                // No need to cancel; subscription is implicitly cancelled.
            }

            // ---------- onComplete ----------
            // Terminal event: the stream finished successfully. No more signals.
            @Override
            public void onComplete() {
                System.out.println("onComplete: stream finished.");
            }
        };

        // =====================================================================
        // 3. WIRING IT TOGETHER: subscribe()
        // =====================================================================
        // This is the trigger. Nothing happens before this line.
        // The Publisher receives the Subscriber, and the whole sequence starts.
        System.out.println(">>> Calling publisher.subscribe(subscriber)...");
        publisher.subscribe(subscriber);
        System.out.println("<<< subscribe() call returned.\n");

        // Note: For a cold publisher like Flux.just(), the data is emitted
        // synchronously inside the subscribe() call, so by the time we reach
        // this point, all onNext/onComplete have already executed.
        // That's why we see all output before the final print.
    }
}