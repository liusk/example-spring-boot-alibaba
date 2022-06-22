package com.example.streamkafka;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.function.Consumer;

@SpringBootApplication
public class StreamKafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(StreamKafkaApplication.class, args);
	}
	@Bean
	public Consumer<Person> log() {
		return person -> {
			System.out.println("Received: " + person);
		};
	}
}
