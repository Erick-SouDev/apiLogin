package erick.br.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import erick.br.model.Usuario;
import erick.br.services.ServicesDaoUsuario;

@RestController
@RequestMapping(produces = "application/json", value = { "/api/usuario"})
@CrossOrigin(origins = "*")
public class ControleRegisterUsuario {

	
	@Autowired
	ServicesDaoUsuario servicesDaoUsuario;
	
	@PostMapping("/create")
	public ResponseEntity<Usuario> cresterNewUser(@RequestBody Usuario usuario) {
		Usuario newUser = servicesDaoUsuario.createUser(usuario);
		return new ResponseEntity<Usuario>(newUser,HttpStatus.CREATED);

	}
}