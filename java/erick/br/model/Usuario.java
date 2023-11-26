package erick.br.model;


import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.ForeignKey;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;

@Entity
@SequenceGenerator(name = "seq_id", sequenceName = "seq_id", initialValue = 100, allocationSize = 1)
@Table(name = "usuario")
public class Usuario  implements UserDetails {

	
	private static final long serialVersionUID = 1L;


	public Usuario(String nome, String email, String senha) {
		this.nome = nome;
		this.email = email;
		this.senha = senha;
	}


	public Usuario() {
		super();
	}

	@Id
	@GeneratedValue(generator = "seq_id", strategy = GenerationType.SEQUENCE)
	private Long id;

	@Column(nullable = false, length = 300, name = "nome")
	private String nome;

	@Column(nullable = false, length = 300, name = "email")

	private String email;

	@Column(nullable = false, length = 300, name = "senha")

	private String senha;
	
	
	@OneToMany(cascade = CascadeType.REFRESH , orphanRemoval = true  , fetch = FetchType.EAGER)
	@JoinTable(name = "usuario_role" , uniqueConstraints = 
	@UniqueConstraint(columnNames = { "usuario_id" , "role_id" } ,name = "constraint_user_role"),
	 joinColumns =        @JoinColumn(name  = "usuario_id" , referencedColumnName = "id" , table = "Usuario" ,foreignKey = @ForeignKey(name = "usuario_fk")) 
	,inverseJoinColumns = @JoinColumn(name  = "role_id" ,  referencedColumnName = "id" , table = "role"  , foreignKey = @ForeignKey(name = "role_fk") ))
	
	private List<Role> roles = new ArrayList<>();

    public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
    public List<Role> getRoles() {
		return roles;
	}

	
	@Override
	public String toString() {
		return "Usuario [id=" + id + ", nome=" + nome + ", email=" + email + ", senha=" + senha + "]";
	}

	

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(id, other.id);
	}


	public Long getId() {
		return id;
	}


	public void setId(Long id) {
		this.id = id;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getSenha() {
		return senha;
	}


	public void setSenha(String senha) {
		this.senha = senha;
	}

	

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return this.roles;
	}


	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return this.senha;
	}


	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.email;
	}


	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}


	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	
	
	
	

}
