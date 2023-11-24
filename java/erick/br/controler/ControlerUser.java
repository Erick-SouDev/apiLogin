package erick.br.controler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import erick.br.model.Usuario;
import erick.br.repository.RepositoryUsuario;

@RestController
@RequestMapping(value = {"/"} , produces = {"application/json"} )
public class ControlerUser {

	@Autowired
	RepositoryUsuario repositoryUsuario;
	
	
	@GetMapping(value = {"/usuario"})
	public ResponseEntity<String> getHello(){
		
		return new ResponseEntity<String>("Ola ", HttpStatus.OK);
	}
	
	@PostMapping(value = {"/create"})
	public ResponseEntity<Usuario> createNewUser(@RequestBody Usuario usuario){
		 String senhaCriptogrfada = new BCryptPasswordEncoder().encode(usuario.getSenha());
		 usuario.setSenha(senhaCriptogrfada);
		 Usuario newUser = repositoryUsuario.save(usuario);
		 return new  ResponseEntity<Usuario>(newUser, HttpStatus.CREATED);
	}
}
