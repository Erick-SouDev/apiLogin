package erick.br.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import jakarta.servlet.http.HttpServletResponse;

@RestController
@RequestMapping(value = { "/" })
@CrossOrigin(origins = "http://localhost:4200/")

public class ControllerUser {

	public ControllerUser(AuthenticationManager authenticationManager) {
		this.authenticationManager = authenticationManager;
	}

	@Autowired
	private ServicesDaoUsuario servicesDaoUsuario;

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private TokenServices tokenServices;

	@GetMapping(value = { "/permitido" })
	public String teste() {
		return "esta permitido";

	}

	@PostMapping(value = { "/create" })
	public ResponseEntity<Usuario> createNewUser(@RequestBody Usuario usuario, HttpServletResponse response) {

		return new ResponseEntity<Usuario>(servicesDaoUsuario.createNewUser(usuario), HttpStatus.CREATED);

	}

	@SuppressWarnings("unused")
	@PostMapping(value = { "/login" })
	public ResponseEntity autenticar(@RequestBody UserDto dto) {
	
		var tokenGerado = tokenServices.createToken(authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(dto.getEmail(),dto.getSenha())));
		return new ResponseEntity<>(new DtoToken(tokenGerado),  HttpStatus.CREATED);

	}

}
