package dev.shafin.reliability_hub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "dev.shafin")
public class ReliabilityHubApplication {

	public static void main(String[] args) {
		SpringApplication.run(ReliabilityHubApplication.class, args);
	}

}
