package cat.itacademy.barcelonactiva.FrancoToda.Pau.s05.t02.security.model;

import jakarta.persistence.Id;

import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Table;

@Entity
@Table
public class User implements UserDetails{

	@Id
	private long id;
	
	private String nom;
	
	private String contrasenya;
	
	@Enumerated(EnumType.STRING)
	private Role rol;
	
	public User() {}
	
	public User(long id, String nom, String contrasenya) {
		this.id = id;
		this.nom = nom;
		this.contrasenya = contrasenya;
	}

	public long getId() {
		return id;
	}

	public String getNom() {
		return nom;
	}

	public String getContrasenya() {
		return contrasenya;
	}

	public void setId(long id) {
		this.id = id;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public void setContrasenya(String contrasenya) {
		this.contrasenya = contrasenya;
	}

	
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return List.of(new SimpleGrantedAuthority(rol.name()));
	}

	@Override
	public String getPassword() {
		return contrasenya;
	}

	@Override
	public String getUsername() {
		return nom;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
	
	
}
