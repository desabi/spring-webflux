package com.desabisc.guide.java.reactor.cflux;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;
import reactor.core.publisher.Flux;

/**
 * There is the short version using lambdas.
 */
public class ReactiveInternalsDemo {

    public static void main(String[] args) {

        // 1. THE PUBLISHER
        // This is our data source. It holds 5 items. 
        // Right now, it is completely idle. No data is moving.
        Flux<String> warehouse = Flux.just("Laptop", "Monitor", "Keyboard", "Mouse", "Desk");

        // 2. THE SUBSCRIBER
        // We are building a custom consumer to handle the data flow manually.
        Subscriber<String> retailStore = new Subscriber<String>() {

            // We MUST save the subscription locally so we can ask for more data later.
            private Subscription activeSubscription;
            private int receivedCount = 0;

            // Task 1: The Handshake
            @Override
            public void onSubscribe(Subscription subscription) {
                System.out.println("[Store] Handshake complete! Saving the contract.");
                this.activeSubscription = subscription;
                
                // Task 2: The Initial Demand (Backpressure)
                System.out.println("[Store] We only have shelf space for 2 items. Requesting 2...");
                this.activeSubscription.request(2); 
            }

            // Task 3: Processing the Data
            @Override
            public void onNext(String item) {
                receivedCount++;
                System.out.println("[Store] Received delivery: " + item);

                // Task 4: Requesting more when ready
                // Once we process the first 2 items, we clear shelf space and ask for the rest.
                if (receivedCount == 2) {
                    System.out.println("[Store] First batch processed. Requesting 3 more...");
                    this.activeSubscription.request(3);
                }
            }

            // Task 5: Handling Failures
            @Override
            public void onError(Throwable throwable) {
                System.err.println("[Store] Delivery failed: " + throwable.getMessage());
            }

            // Task 6: Graceful Shutdown
            @Override
            public void onComplete() {
                System.out.println("[Store] All expected inventory received. Shutting down.");
            }
        };

        // 3. THE TRIGGER
        // This is the spark that ignites the pipeline. Until this line executes, 
        // the warehouse does nothing and the retailStore receives nothing.
        System.out.println("--- INITIATING CONNECTION ---");
        warehouse.subscribe(retailStore);
    }
}