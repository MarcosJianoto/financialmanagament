package com.personalfinancial.entities;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "users_financial")
public class UsersFinancial implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_users_financial")
	@SequenceGenerator(name = "seq_users_financial", sequenceName = "seq_users_financial", allocationSize = 1)
	@Column(name = "id")
	private Long id;

	@Column(name = "email")
	private String email;

	@Column(name = "password")
	private String password;

	@Column(name = "role")
	@Enumerated(EnumType.STRING)
	private UserRole role;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public UserRole getRole() {
		return role;
	}

	public void setRole(UserRole role) {
		this.role = role;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {

		List<GrantedAuthority> authorities = new ArrayList<>();

		if (this.role == UserRole.ADMIN)
			authorities.add(new SimpleGrantedAuthority("ADMIN"));

		if (this.role == UserRole.PREMIUM)
			authorities.add(new SimpleGrantedAuthority("PREMIUM"));

		if (this.role == UserRole.STANDARD)
			authorities.add(new SimpleGrantedAuthority("STANDARD"));

		return authorities;

	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

}
