package com.app.org.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSetter;
@Entity
public class AppUser implements Serializable{
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	@Column(unique=true)
	private String username;
	
	private String password;
	
	@ManyToMany(fetch=FetchType.EAGER)
	private Collection<Role> roles=new HashSet<>();
	
	public AppUser() {
	}

	public AppUser(String username, String password) {
		super();
		this.username = username;
		this.password = password;
	}


	public AppUser(String username, String password, Collection<Role> roles) {
		super();
		this.username = username;
		this.password = password;
		this.roles = roles;
	}

	@JsonIgnore
	public String getPassword() {
		return password;
	}

	@JsonSetter
	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getUsername() {
		return username;
	}

	public Collection<Role> getRoles() {
		return roles;
	}

	public void setRoles(Collection<Role> roles) {
		this.roles = roles;
	}

	
	
}
