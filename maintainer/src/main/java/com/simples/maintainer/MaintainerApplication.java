package com.simples.maintainer;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@Slf4j
@SpringBootApplication
public class MaintainerApplication {

	public static void main(String[] args) {
		SpringApplication.run(MaintainerApplication.class, args);
		log.info("Swagger UI: http://localhost:8080/swagger-ui/index.html");
	}

}
