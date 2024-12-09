package com.personalfinancial.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.personalfinancial.entities.Expenses;
import com.personalfinancial.entities.Revenues;

public interface ExpensesRepository extends JpaRepository<Expenses, Long> {

	List<Expenses> findByUsersFinancial_Id(Long idUser);

}
