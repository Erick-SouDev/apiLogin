package erick.br.services;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;

import erick.br.repository.RepositoryUsuario;

@Service
@SuppressWarnings("deprecation")
public class TokenServices {

	@Value("${jwt.secret}")
	private String secret;
	@Value("${jwt.expiration}")
	private Long expiration;

	@Autowired
	private RepositoryUsuario repositoryUsuario;

	public String createToken(Authentication userName) {
		
       User user = (User) userName.getPrincipal();
		try {
			return JWT.create().withSubject(user.getUsername()).sign(Algorithm.HMAC512(this.secret));
		} catch (JWTCreationException e) {
			throw new RuntimeException("UM ERRO OCORREU NA CRIAÇÃO DO TOKEN" + e, new Throwable().getCause());
		}

	}

	public String getSubjectUser(String tokenUser) {
		try {

			return JWT.require(Algorithm.HMAC512(this.secret)).build().verify(tokenUser).getSubject();
		} catch (JWTVerificationException e) {
			throw new RuntimeException("ERRO AO VERIFICAR TOKEN" + e);
		}
	}

	private Date dataExpirationn() {
		// TODO Auto-generated method stub
		return new Date(System.currentTimeMillis() + this.expiration);
	}

}
