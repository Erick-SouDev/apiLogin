package erick.br.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import erick.br.model.Usuario;
import erick.br.repository.RepositoryUsuario;

@Service
public class ServicesDaoUsuario {

	@Autowired
	private RepositoryUsuario repositoryUsaurio;

	public Usuario createUser(Usuario usuario) {
		return repositoryUsaurio.save(usuario);
	}
}
