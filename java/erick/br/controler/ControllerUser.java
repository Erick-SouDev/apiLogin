package erick.br.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import erick.br.model.DtoToken;
import erick.br.model.UserDto;
import erick.br.model.Usuario;
import erick.br.services.ServicesDaoUsuario;
import erick.br.services.TokenServices;

@RestController
@RequestMapping(value = { "/" })
public class ControllerUser {

	public ControllerUser(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Autowired
	private ServicesDaoUsuario servicesDaoUsuario;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenServices servicesCreateToken;

	@GetMapping(value = { "/permitido" })
	public ResponseEntity<String> getHello() {
		return ResponseEntity.ok("OLA JWT ESTA LIBERADA ESSA ROTA ");
	}

	@PostMapping(value = { "/create" })
	public ResponseEntity<Usuario> createNewUser(@RequestBody Usuario usuario) {

		return new ResponseEntity<Usuario>(servicesDaoUsuario.createNewUser(usuario), HttpStatus.CREATED);
	}

	@PostMapping("/login")
	public ResponseEntity<DtoToken> authenticationUser(@RequestBody UserDto dto) {
		UsernamePasswordAuthenticationToken userAuthenticatio = new UsernamePasswordAuthenticationToken(dto.getEmail(),
				dto.getSenha());
		Authentication userAuthenticationManager = authenticationManager.authenticate(userAuthenticatio);

		var tokenGerado = servicesCreateToken.createToken(userAuthenticationManager);
		return new ResponseEntity<DtoToken>(new DtoToken(tokenGerado), HttpStatus.CREATED);

	}
}
