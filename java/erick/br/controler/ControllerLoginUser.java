package erick.br.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import erick.br.model.DtoToken;
import erick.br.model.UserDto;
import erick.br.services.jwt.ServicesCreateToken;

@RestController
@RequestMapping(value = { "/auth" }, produces = { "application/json" })
public class ControllerLoginUser {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private ServicesCreateToken servicesCreateToken;

	@PostMapping("/login")
	public ResponseEntity<DtoToken> authenticationUser(@RequestBody UserDto dto) {

		var userAuthenticatio = new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getSenha());
		var usuarioAutenticado = authenticationManager.authenticate(userAuthenticatio);
		
		String tokenGerador = servicesCreateToken.createToken(userAuthenticatio);

		return new ResponseEntity<DtoToken>(new DtoToken(tokenGerador), HttpStatus.OK);

	}
}
