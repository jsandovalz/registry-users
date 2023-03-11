package com.nisum.registryusers;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
@OpenAPIDefinition(info = @Info(title = "API RESTful to registry users", version = "1.0", description = "Test by Jose Sandoval"))
public class RegistryUsersApplication {
	public static void main(String[] args) {
		SpringApplication.run(RegistryUsersApplication.class, args);
	}
}
