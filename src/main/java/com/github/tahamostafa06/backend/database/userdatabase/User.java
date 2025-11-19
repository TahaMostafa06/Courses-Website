package com.github.tahamostafa06.backend.database.userdatabase;

import com.github.tahamostafa06.backend.database.common.Record;

public class User implements Record {
	private String role;
	private String username;
	private String email;
	private String passwordHash;

	User(String role, String username, String email, String passwordHash) {
		this.role = role;
		this.username = username;
		this.email = email;
		this.passwordHash = passwordHash;
	}

	public String getRole() {
		return role;
	}

	public String getUsername() {
		return username;
	}

	public String getEmail() {
		return email;
	}

	public String getPasswordHash() {
		return passwordHash;
	}
}
