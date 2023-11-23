package erick.br.config.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
@SuppressWarnings({ "removal", "deprecation" })
public class ConfigSecurity {

	@Bean
	SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

		  http.authorizeHttpRequests( (authorize) -> authorize
		           .requestMatchers(HttpMethod.GET , "/").permitAll()
		           .requestMatchers(HttpMethod.POST,"/api/usuario/create").permitAll()
		           .anyRequest().authenticated())
		     
		       .sessionManagement((sesseion)-> sesseion
		    		 .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
		     
		               .logout((logout)-> logout.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		            		   .logoutSuccessUrl("/"));
		
		
		return http.build();
				
				

	}

}
