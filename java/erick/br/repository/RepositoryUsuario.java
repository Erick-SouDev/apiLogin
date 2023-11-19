package erick.br.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import erick.br.model.Usuario;

@Transactional
@Repository("repo_user")

public interface RepositoryUsuario extends CrudRepository<Usuario, Long> {
	@Query("select u from u where u.email = ?1")/*aqui no caso o login e o email cadastrado no banco*/
	public Usuario findUserByLogin(@Param("login") String login);
}
