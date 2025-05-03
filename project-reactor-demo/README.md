# Reactive Streams in Java
Reactive Streams is a standard for asynchronous stream processing with non-blocking back pressure, introduced in Java 9 as part of the **java.util.concurrent.Flow** API. It provides a common interface for reactive programming libraries to ensure interoperability.

## Key Interfaces (in java.util.concurrent.Flow)
Reactive Streams defines four interfaces:

- **Publisher<T>:** Produces data and sends it to Subscriber on demand.
- **Subscriber<T>:**	Consumes data and reacts to events (onNext, onError, etc.).
- **Subscription:**	Link between Publisher and Subscriber; used for requesting items.
- **Processor<T,R>:**	A combination of Subscriber and Publisher (acts as a bridge).

# How it works (Flow Overview)
1. Subscriber subscribes to a Publisher.
2. Publisher sends a Subscription to the Subscriber.
3. Subscriber requests N items (back pressure mechanism).
4. Publisher emits up to N items via onNext().
5. If something goes wrong, onError() is called.
6. Once complete, onComplete() is called.

## https://engineering.linecorp.com/en/blog/reactive-streams-armeria-1

1. A **subscriber** uses the ***subscribe*** function to request a ***subscription*** to the **publisher**.
2. The **publisher** uses the ***onSubscribe*** function to send the ***subscription*** to the **subscriber**.
3. The ***subscription*** now acts as a **medium** between a **subscriber** and **publisher**. Subscribers do not directly request data from publishers. **Requests are sent to publishers using the ***request*** function of the subscription.**
4. **The publisher**, using subscription, sends data with ***onNext***, ***onComplete*** for completed tasks, and ***onError*** for errors.
5. The subscriber, publisher, and subscription all form an organic connection, communicating with each other; starting from subscribe all the way to onComplete. This completes the back pressure structure.
