package com.personalfinancial.entities;

public enum UserRole {

	STANDARD("standard"), PREMIUM("premium"), ADMIN("admin");

	private String role;

	UserRole(String role) {
		this.role = role;
	}

	public String getRole() {
		return role;
	}

}
