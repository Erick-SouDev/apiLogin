package erick.br.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import erick.br.model.Usuario;
import erick.br.repository.RepositoryUsuario;
import erick.br.services.ServicesDaoUsuario;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping(value = {"/usuario"} , produces = {"application/json"} )
public class ControllerUser {

	@Autowired
	ServicesDaoUsuario servicesDaoUsuario;
	
	
	@GetMapping(value = {"/ola"})
	public ResponseEntity<String> getHello(){
		
		return new ResponseEntity<String>("Ola ", HttpStatus.OK);
	}
	
	@PostMapping(value = {"/create"})
	public ResponseEntity<Usuario> createNewUser(@RequestBody Usuario usuario){
		
		 return new  ResponseEntity<Usuario>(servicesDaoUsuario.createNewUser(usuario), HttpStatus.CREATED);
	}
}
