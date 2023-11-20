package erick.br.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class ConfigSecurity {

	@Bean
    SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
		.csrf((c)->c.disable())
		     .sessionManagement((s)-> s.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		          .authorizeHttpRequests((auth)-> auth
		        		  .requestMatchers(HttpMethod.POST , "/api/usuario/create").permitAll())
		                      ;

		return http.build();
	}

}
