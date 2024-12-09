package com.personalfinancial.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.personalfinancial.repositories.UsersFinancialRepository;

@Service

public class AuthorizationService implements UserDetailsService {

	@Autowired
	UsersFinancialRepository usersFinancialRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return usersFinancialRepository.findByEmail(username);
	}

}