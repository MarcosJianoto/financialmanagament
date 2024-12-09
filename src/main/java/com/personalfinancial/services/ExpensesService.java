package com.personalfinancial.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.personalfinancial.dto.ExpensesDTO;
import com.personalfinancial.entities.Category;
import com.personalfinancial.entities.Expenses;
import com.personalfinancial.entities.Revenues;
import com.personalfinancial.entities.UsersFinancial;
import com.personalfinancial.repositories.CategoryRepository;
import com.personalfinancial.repositories.ExpensesRepository;
import com.personalfinancial.repositories.UsersFinancialRepository;

@Service
public class ExpensesService {

	@Autowired
	private ExpensesRepository expensesRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private UsersFinancialRepository usersFinancialRepository;

	public void saveExpenses(ExpensesDTO expensesDTO) {

		Optional<Category> findCategory = categoryRepository.findById(expensesDTO.getCategoryId());
		Optional<UsersFinancial> findUser = usersFinancialRepository.findById(expensesDTO.getUsersFinancialId());

		if (findCategory.isPresent() && findUser.isPresent()) {
			Expenses expenses = new Expenses();
			expenses.setCategory(findCategory.get());
			expenses.setUsersFinancial(findUser.get());
			expenses.setDescription(expensesDTO.getDescription());
			expenses.setAmount(expensesDTO.getAmount());
			expenses.setDateHourFinancial(LocalDateTime.now());
			expenses.setPaymentMethod(expensesDTO.getPaymentMethod());
		}
	}

	public void updateExpenses(Long id, ExpensesDTO expensesDTO) {

		Optional<Expenses> findRevenuesId = expensesRepository.findById(id);
		Optional<UsersFinancial> findUserId = usersFinancialRepository.findById(expensesDTO.getUsersFinancialId());
		Optional<Category> findCategoryId = categoryRepository.findById(expensesDTO.getCategoryId());

		if (findRevenuesId.isPresent() && findUserId.isPresent() && findCategoryId.isPresent()) {

			Revenues revenues = new Revenues();
			revenues.setAmount(expensesDTO.getAmount());
			revenues.setDateHourFinancial(LocalDateTime.now());
			revenues.setDescription(expensesDTO.getDescription());
			revenues.setPaymentMethod(expensesDTO.getPaymentMethod());
			revenues.setUsersFinancial(findUserId.get());
			revenues.setCategory(findCategoryId.get());

			expensesRepository.save(findRevenuesId.get());

		}

	}

	public List<ExpensesDTO> getExpenses(Long idUser) {

		List<Expenses> findUserId = expensesRepository.findByUsersFinancial_Id(idUser);
		List<ExpensesDTO> expensesDTOs = new ArrayList<>();

		for (Expenses expenses : findUserId) {

			ExpensesDTO expensesDTO = new ExpensesDTO();
			expensesDTO.setAmount(expenses.getAmount());
			expensesDTO.setDescription(expenses.getDescription());
			expensesDTO.setDateHourFinancial(expenses.getDateHourFinancial().toString());
			expensesDTO.setPaymentMethod(expenses.getPaymentMethod());
			expensesDTO.setCategoryId(expenses.getCategory().getId());
			expensesDTO.setUsersFinancialId(expenses.getId());
			expensesDTOs.add(expensesDTO);

		}

		return expensesDTOs;

	}

	public void deleteExpenses(Long id) {
		expensesRepository.deleteById(id);
	}

}
