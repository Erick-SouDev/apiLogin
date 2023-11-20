package erick.br.services;

import java.io.IOException;
import java.util.Date;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("deprecation")
@Service
@Component
public class ServicesAuthenticationJsonWebToken {

	private static final long TIME_EXPIRATION = 172800000;

	private static final String SECRET_PASSWORD = "WgwbkKNQZZnmc69hgDwPoZ0XE1BcRYPDuKyQZoelidA=";

	@SuppressWarnings("unused")
	private static final String TOKEN_PREFIX = "Bearer";

	private static final String HEADER_RESPONSE = "Authorization";

	public void authorizationToken(HttpServletResponse response, String userName) throws IOException {

		String jwt = Jwts.builder() /*GERAR O TOKEN*/
				.setSubject(userName) /*SETA O USUARIO*/
				.setExpiration(new Date(System.currentTimeMillis() + TIME_EXPIRATION))/*PASSA O TEMPO LIMITE DE EXPiRACAO DO TOKEN*/
				.signWith(SignatureAlgorithm.ES512, SECRET_PASSWORD).compact(); /*COMPACTA GERANDO A STRING DE AUTHENTICAO CRIPTOGRAFADA*/

		String token = TOKEN_PREFIX + "" + jwt; /*JUNTA O A CHAVE COM O VALOR */
		response.setHeader(HEADER_RESPONSE, token);/*ESCREVE O TOKEN NO HEADER DE REPOSTA OU CABEÇALHO SE ASSIM QUEIRA CHAMAR*/
		
		// GERAR RESPOSTA DO TOKEN EM JSON
		response.getWriter().write("{\"Authorization\" : \""+token+"\"}");
	}

}
