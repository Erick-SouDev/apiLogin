package erick.br.services.jwt;

import java.io.IOException;
import java.util.Date;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import erick.br.model.Usuario;
import erick.br.repository.RepositoryUsuario;
import erick.br.util.GetContextApplication;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Service
@Component

public class JwtServicesCreateToken {

	private static final String SECRETS_KEY = "7lzjh9Nj9lTTZpoybd4cx/s9JCl615DbitHWzp6hA2s=";

	private static final String PREFIX = "Bearer";

	private static final String HEADER = "Authorization";

	// Metodo Para Gerar O token e adcionar a resposta da requisicao
	public void createAuthenticationJwt(HttpServletResponse response, String userName) throws IOException {
		try {

			String jwtToken = Jwts.builder().setSubject(userName)
					.setExpiration(new Date(System.currentTimeMillis() + 2592000000l))
					.signWith(SignatureAlgorithm.ES512, SECRETS_KEY).compact();

			String tokenGerado = PREFIX + jwtToken;

			response.setHeader(HEADER, tokenGerado);

			response.getWriter().write("{\"Authorization\" : \"" + tokenGerado + "\"}");

		} catch (MalformedJwtException e) {
			throw new MalformedJwtException("Erro ao gerar  TOKEN" + e);
		}

	}

	// Autentica o token
	public Authentication getAuthenticationJwt(HttpServletRequest request) {

		String token = request.getHeader(HEADER);

		if (token != null) {
			// retorn o usuario 
			String user = Jwts.parser().setSigningKey(SECRETS_KEY).parseClaimsJwt(token.replace(PREFIX, "")).getBody()
					.getSubject();

			if (user != null && !user.isEmpty()) {
				// ACESSAO CONTEXTO E A CLASSE DE REPOSITORIO DO USUARIO
				Usuario usuarioLogadoAuthenticado = GetContextApplication.context.getBean(RepositoryUsuario.class)
						.findUserByLogin(user);
				if (usuarioLogadoAuthenticado != null) {
					return new UsernamePasswordAuthenticationToken(usuarioLogadoAuthenticado.getEmail(), usuarioLogadoAuthenticado.getSenha(), usuarioLogadoAuthenticado.getAuthorities());
				}

			}
		}

		return null;
	}

}
