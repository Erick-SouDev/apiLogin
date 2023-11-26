package erick.br.services.jwt;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import erick.br.repository.RepositoryUsuario;

@Service
@SuppressWarnings("deprecation")
public class ServicesCreateToken {

	private static final String SECRET_KAY = "SENHASECRETA";

	@Autowired
	RepositoryUsuario repositoryUsuario;

	public String createToken(Authentication userName) {
		try {

			return JWT.create().withExpiresAt(dataExpirationn()).withSubject((String) userName.getPrincipal())
					.sign(Algorithm.HMAC512(SECRET_KAY));

		} catch (JWTCreationException e) {
			// TODO: handle exception
			throw new JWTCreationException("ERRO AO GERAR TOKEN", e);
		}
	}

	public String getToken(String tokenUser) {

		try {

			return JWT.require(Algorithm.HMAC512(SECRET_KAY)).build().verify(tokenUser).getSubject();
		} catch (JWTVerificationException e) {
			// TODO: handle exception
			throw new JWTVerificationException("TOKEN INVALIDO", e);
		}

	}

	private Date dataExpirationn() {
		// TODO Auto-generated method stub
		return new Date(System.currentTimeMillis() + 172800000);
	}

}
