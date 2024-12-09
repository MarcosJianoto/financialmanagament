package com.personalfinancial.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.personalfinancial.entities.Revenues;

public interface RevenuesRepository extends JpaRepository<Revenues, Long> {

	List<Revenues> findByUsersFinancial_Id(Long idUser);

}
