package erick.br.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class ConfigCrosOriginGlobal implements WebMvcConfigurer {

	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("*").allowCredentials(true).allowedHeaders("*").allowedOrigins("*").allowedMethods("POST",
				"GET", "DELETE", "PUT");

	}
}
