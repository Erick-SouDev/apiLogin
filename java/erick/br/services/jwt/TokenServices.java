package erick.br.services.jwt;

import java.util.Date;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@Service
@SuppressWarnings("deprecation")
public class TokenServices {

	private static final String SECRET_KAY = "U0VOSEFTRUNSRVRB";

	public String createToken(Authentication userName) {

		Algorithm algorithm = Algorithm.HMAC512(SECRET_KAY);
		String tokenUser = JWT.create().withSubject(userName.getPrincipal().toString())
				.withIssuer("usuario").sign(algorithm);

		return tokenUser;

	}

	public String getSubjectUser(String tokenUser) {
		return tokenUser;

	}

	private Date dataExpirationn() {
		// TODO Auto-generated method stub
		return new Date(System.currentTimeMillis() + 172800000);
	}

}
