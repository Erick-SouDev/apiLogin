package erick.br.config.security.jwt;

import java.io.IOException;
import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletResponse;

@Service
@Component

public class JwtServicesCreateToken {

	private static final String SECRETS_KEY = "7lzjh9Nj9lTTZpoybd4cx/s9JCl615DbitHWzp6hA2s=";

	private static final String PREFIX = "Bearer";

	private static final String HEADER_RESPONSE = "Authorization";

	// Metodo Para Gerar O token e adcionar a resposta da requisicao 
	public void createAuthenticationJwt(HttpServletResponse response, String userName) throws IOException {
		try {

		String jwtToken = Jwts.builder().setSubject(userName)
				.setExpiration(new Date(System.currentTimeMillis() + 2592000000l))
				.signWith(SignatureAlgorithm.ES512, SECRETS_KEY).compact();

		String tokenGerado = PREFIX + jwtToken;
		
		response.setHeader(HEADER_RESPONSE, tokenGerado);
		
		response.getWriter().write("{\"Authorization\" : \""+tokenGerado+"\"}");
		
		
		}catch (MalformedJwtException e) {
			throw new MalformedJwtException("Erro ao gerar  TOKEN");
		}

	}

}
