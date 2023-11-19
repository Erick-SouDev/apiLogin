package erick.br.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import erick.br.model.Usuario;
import erick.br.repository.RepositoryUsuario;

@Service
@Transactional
public class ServiceAutenticarUser implements UserDetailsService {
	@Autowired
	RepositoryUsuario repositoryUsuario;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Usuario userLogado = repositoryUsuario.findUserByLogin(username);
		if(userLogado == null) {
			throw new  UsernameNotFoundException("Usuario Não Encontrado");
		}
		return new User(userLogado.getEmail() , userLogado.getPassword() , userLogado.getAuthorities());
	}

}
