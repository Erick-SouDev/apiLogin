package erick.br.services;

import java.io.IOException;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import erick.br.model.Usuario;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
public class JwtServicesLogin extends AbstractAuthenticationProcessingFilter {

	


	public JwtServicesLogin(String url , AuthenticationManager authenticationManager) {
		super(new AntPathRequestMatcher(url));
		setAuthenticationManager(authenticationManager);
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {
		// CASO USUARIO ESTAJA AUTHENTICADO ELE PODERA FAZER QUALQUE OPERACAO EXMPLO UMA COSULATA NO BANCO UM DELETE ETC
		
		Usuario  user = new  ObjectMapper().readValue(request.getInputStream(), Usuario.class);
		
		return getAuthenticationManager()
				.authenticate(new UsernamePasswordAuthenticationToken(
						          user.getEmail()
								 ,user.getSenha() 
								, user.getAuthorities()));
	}
	
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
	     new JwtServicesAuthentication().authenticationToken(response, authResult.getName());
	}

}
