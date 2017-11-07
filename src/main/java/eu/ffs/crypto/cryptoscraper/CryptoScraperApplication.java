package eu.ffs.crypto.cryptoscraper;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

@SpringBootApplication
@EnableScheduling
@EnableJpaRepositories
public class CryptoScraperApplication {

	public static void main(String[] args) {
		SpringApplication.run(CryptoScraperApplication.class, args);
	}

	@Bean
	public RestTemplate restTemplate() {
		return new RestTemplateBuilder().build();
	}

}
