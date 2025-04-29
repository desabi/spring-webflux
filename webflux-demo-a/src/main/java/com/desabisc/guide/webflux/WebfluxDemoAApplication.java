package com.desabisc.guide.webflux;

import com.desabisc.guide.webflux.client.GreetingClient;
import com.desabisc.guide.webflux.model.Greeting;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * <a href="https://spring.io/guides/gs/reactive-rest-service">Resource</a>
 */
@SpringBootApplication
@Slf4j
public class WebfluxDemoAApplication {

	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(WebfluxDemoAApplication.class, args);
		GreetingClient greetingClient = context.getBean(GreetingClient.class);
		// We need to block for the content here or the JVM might exit before the message is logged.
		System.out.println(">> Message = " + greetingClient.getMessage().block());
	}

}
