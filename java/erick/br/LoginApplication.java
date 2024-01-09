package erick.br;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "erick.br.repository")
@ComponentScan(basePackages = { "erick.br.*" })
@EntityScan(basePackages = { "erick.br.model" })
public class LoginApplication implements WebMvcConfigurer {

	public static void main(String[] args) {
		SpringApplication.run(LoginApplication.class, args);

	}

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**").allowedOrigins("*").allowedHeaders("*").allowedOriginPatterns("*")

				.allowedMethods("GET", "POST", "PUT", "DELETE").allowCredentials(false);

	}

}
