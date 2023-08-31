package br.com.matheuszanatta.consumerother;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ConsumerOtherApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerOtherApplication.class, args);
	}

}
