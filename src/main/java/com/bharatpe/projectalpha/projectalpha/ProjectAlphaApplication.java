package com.bharatpe.projectalpha.projectalpha;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.annotation.PropertySources;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@ComponentScan(basePackages =  "com.bharatpe.*")
@EntityScan(basePackages = "com.bharatpe.*")
@EnableJpaRepositories(basePackages = "com.bharatpe.*")
@PropertySources({
		@PropertySource("file:/etc/bharatpe/key.properties"),
		@PropertySource("file:/etc/bharatpe/production.properties")
})
@SpringBootApplication
public class ProjectAlphaApplication {

	public static void main(String[] args) {
		SpringApplication.run(ProjectAlphaApplication.class, args);
	}

}
