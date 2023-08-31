package br.com.matheuszanatta.invext;

import org.springframework.amqp.rabbit.annotation.EnableRabbit;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@EnableRabbit
@SpringBootApplication
public class InvextApplication {

	public static void main(String[] args) {
		SpringApplication.run(InvextApplication.class, args);
	}

}
