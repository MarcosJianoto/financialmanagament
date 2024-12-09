package com.personalfinancial.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

import com.personalfinancial.entities.UsersFinancial;

public interface UsersFinancialRepository extends JpaRepository<UsersFinancial, Long> {

	UserDetails findByEmail(String email);

	Optional<UsersFinancial> findEntityByEmail(String email);

}