package erick.br.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ConfigSpringUtil {

	
	@Bean("restTemplate")
	public RestTemplate restTemplate() {
		return new RestTemplate();
	}
	
	@Bean("bCript")
	public PasswordEncoder encoder() {
		return new BCryptPasswordEncoder();
	}
	
	
}
