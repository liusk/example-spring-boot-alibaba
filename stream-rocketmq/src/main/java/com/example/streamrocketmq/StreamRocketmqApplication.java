package com.example.streamrocketmq;

import com.example.streamrocketmq.controller.Animal;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Bean;

import java.util.function.Consumer;

/**
 * @author liusk
 */
@EnableBinding({Source.class, Sink.class})
@SpringBootApplication
public class StreamRocketmqApplication {

	public static void main(String[] args) {
		SpringApplication.run(StreamRocketmqApplication.class, args);
	}

//	@Bean
//	public Consumer<Person> person() {
//		return person -> {
//			System.out.println("Received: " + person);
//		};
//	}

	@Bean
	public Consumer<Animal> animal() {
		return animal -> {
			System.out.println("Received: " + animal);
		};
	}


}
