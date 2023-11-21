package erick.br.services;

import java.io.IOException;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationTrustResolverImpl;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import erick.br.model.Usuario;
import erick.br.repository.RepositoryUsuario;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("deprecation")
@Service
@Component
public class JwtServicesAuthentication  {

	@Autowired
	RepositoryUsuario repositoryUsuario;
	private static final long TIME_EXPIRATION = 172800000;

	private static final String SECRET_PASSWORD = "WgwbkKNQZZnmc69hgDwPoZ0XE1BcRYPDuKyQZoelidA=";

	private static final String TOKEN_PREFIX = "Bearer";

	private static final String HEADER_RESPONSE = "Authorization";
	
	
	
	public Authentication getAuthentication(HttpServletRequest request) {
		
		String token = request.getHeader(HEADER_RESPONSE);
		if (token != null && !token.isEmpty()) {
			String userToken = Jwts.parser().setSigningKey(SECRET_PASSWORD).parseClaimsJws(token.replace(TOKEN_PREFIX, ""))
					.getBody().getSubject();
			
			if (userToken != null && userToken.isEmpty()) {
				
				Usuario userAutenticado = repositoryUsuario.findUserByLogin(userToken);
				return new UsernamePasswordAuthenticationToken(
						userAutenticado.getEmail(), 
						userAutenticado.getSenha(),
						userAutenticado.getAuthorities());
			}
		}
		return null;
		
	}

	public void authenticationToken(HttpServletResponse response, String userName) throws IOException {

		String jwt = Jwts.builder() /* GERAR O TOKEN */
				.setSubject(userName) 
				.setExpiration(new Date(System.currentTimeMillis() + TIME_EXPIRATION)) //tempo de expiracao 
				.signWith(SignatureAlgorithm.ES512, SECRET_PASSWORD) // passar o algoritmo de criptografia + a senha secreta
				.compact(); 

		String token = TOKEN_PREFIX + "" + jwt; /* JUNTA O A CHAVE COM O VALOR */
		response.setHeader(HEADER_RESPONSE,token);/* ESCREVE O TOKEN NO HEADER DE REPOSTA OU CABEÇALHO SE ASSIM QUEIRA CHAMAR */

		// GERAR RESPOSTA DO TOKEN EM JSON
		response.getWriter().write("{\"Authorization\" : \"" + token + "\"}");
	}

	
	

}