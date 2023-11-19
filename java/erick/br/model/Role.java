package erick.br.model;

import org.springframework.security.core.GrantedAuthority;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "role")
@SequenceGenerator(initialValue = 100, allocationSize = 1, name = "role_seq", sequenceName = "role_seq")
public class Role implements GrantedAuthority {

	@Id
	@GeneratedValue(generator = "role_seq", strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(name = "role")
	private String nomeRole;

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public String getRole() {
		return this.nomeRole;
	}

	public void setRole(String nomeRole) {
		this.nomeRole = nomeRole;
	}

	@Override
	public String getAuthority() {
		// TODO Auto-generated method stub
		return this.nomeRole;
	}
}
