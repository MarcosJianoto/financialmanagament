package com.personalfinancial.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.personalfinancial.dto.AuthenticationDTO;
import com.personalfinancial.dto.LoginResponseDTO;
import com.personalfinancial.dto.RegisterDTO;
import com.personalfinancial.entities.UsersFinancial;
import com.personalfinancial.repositories.UsersFinancialRepository;
import com.personalfinancial.security.TokenService;

import jakarta.validation.Valid;

@RestController
public class AuthenticationController {

	@Autowired
	public AuthenticationManager authenticationManager;

	@Autowired
	UsersFinancialRepository usersFinancialRepository;

	@Autowired
	TokenService tokenService;

	@PostMapping("/register")
	public ResponseEntity register(@RequestBody @Valid RegisterDTO data) {
		if (this.usersFinancialRepository.findByEmail(data.email()) != null)
			return ResponseEntity.badRequest().build();

		String encryptedPassword = new BCryptPasswordEncoder().encode(data.password());
		UsersFinancial newUser = new UsersFinancial();
		newUser.setEmail(data.email());
		newUser.setPassword(encryptedPassword);
		newUser.setRole(data.role());

		this.usersFinancialRepository.save(newUser);
		return ResponseEntity.noContent().build();

	}

	@PostMapping("/login")
	public ResponseEntity login(@RequestBody @Valid AuthenticationDTO data) {
		var usernamePassword = new UsernamePasswordAuthenticationToken(data.email(), data.password());
		var auth = this.authenticationManager.authenticate(usernamePassword);

		var token = tokenService.generateToken((UsersFinancial) auth.getPrincipal());

		return ResponseEntity.ok(new LoginResponseDTO(token));
	}

}
