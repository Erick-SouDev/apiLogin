package erick.br.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import erick.br.model.Usuario;

@Transactional
@Repository
public interface RepositoryUsuario extends CrudRepository<Usuario, Long> {

	
}
