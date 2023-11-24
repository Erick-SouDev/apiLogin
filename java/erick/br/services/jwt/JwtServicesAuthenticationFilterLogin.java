package erick.br.services.jwt;

import java.io.IOException;

import org.springframework.security.authentication.AuthenticationCredentialsNotFoundException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.fasterxml.jackson.databind.ObjectMapper;

import erick.br.model.Usuario;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class JwtServicesAuthenticationFilterLogin extends AbstractAuthenticationProcessingFilter {

	protected JwtServicesAuthenticationFilterLogin(String url, AuthenticationManager authenticationManager) {
		super(new AntPathRequestMatcher(url));
		setAuthenticationManager(authenticationManager);

	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException, IOException, ServletException {

		Usuario usuario = new ObjectMapper().readValue(request.getInputStream(), Usuario.class);
		if (usuario == null) {
			throw new AuthenticationCredentialsNotFoundException("Credencial invalido");
		}
		return getAuthenticationManager().authenticate(new UsernamePasswordAuthenticationToken(usuario.getEmail(),
				usuario.getSenha(), usuario.getAuthorities()));
	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		new JwtServicesCreateToken().createAuthenticationJwt(response, authResult.getName());

	}
}
