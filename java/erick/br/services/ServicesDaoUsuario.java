package erick.br.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import erick.br.model.Usuario;
import erick.br.repository.RepositoryUsuario;
import jakarta.transaction.Transactional;
@Transactional
@Service
public class ServicesDaoUsuario {

	@Autowired
	private RepositoryUsuario repositoryUsaurio;
	
	

	public Usuario createNewUser(Usuario usuario) {
		 String senhaCriptogrfada = new BCryptPasswordEncoder().encode(usuario.getSenha());
		 usuario.setSenha(senhaCriptogrfada);
		 Usuario newUser = repositoryUsaurio.save(usuario);
		 return newUser;
	}
}
