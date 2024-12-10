package com.personalfinancial.entities;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

public enum UserRole {

	ROLE_STANDARD("ROLE_STANDARD"), ROLE_PREMIUM("ROLE_PREMIUM"), ROLE_ADMIN("ROLE_ADMIN");

	private final String role;

	UserRole(String role) {
		this.role = role;
	}

	public GrantedAuthority asAuthority() {
		return new SimpleGrantedAuthority(role);
	}

}
