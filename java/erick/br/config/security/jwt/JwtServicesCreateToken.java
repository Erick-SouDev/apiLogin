package erick.br.config.security.jwt;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import erick.br.model.Usuario;
import io.jsonwebtoken.Jwts;

@Service
@Component
public class JwtServicesCreateToken {

	private static final String SECRETS_KEY = "ChaveSecretaMuitoSecreta";
	
	
	
}
