package erick.br.config;

import java.io.IOException;
import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import erick.br.repository.RepositoryUsuario;
import erick.br.services.TokenServices;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class SecurityFilterAuthorizationUser extends OncePerRequestFilter {

	@Autowired
	private RepositoryUsuario repositoryUsuario;

	@Autowired
	private TokenServices tokenServices;

	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		String header = request.getHeader("Authorization");

		if (header != null) {
			var tokenUser = header.replace("Bearer ", "");
			var subjectUser = tokenServices.getSubjectUser(tokenUser);
			if (subjectUser != null) {
				var usuarioRetornado = repositoryUsuario.findUserByLogin(subjectUser);
				if (usuarioRetornado != null) {
					var authenticationToken = new UsernamePasswordAuthenticationToken(usuarioRetornado, null,
							usuarioRetornado.getAuthorities());

					SecurityContextHolder.getContext().setAuthentication(authenticationToken);
				}
			}

		}
		filterChain.doFilter(request, response);
	}

}
