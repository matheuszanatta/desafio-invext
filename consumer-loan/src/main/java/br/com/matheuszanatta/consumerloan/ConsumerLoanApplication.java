package br.com.matheuszanatta.consumerloan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@SpringBootApplication
public class ConsumerLoanApplication {

	public static void main(String[] args) {
		SpringApplication.run(ConsumerLoanApplication.class, args);
	}

}
