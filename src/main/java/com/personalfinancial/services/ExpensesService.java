package com.personalfinancial.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.personalfinancial.dto.ExpensesDTO;
import com.personalfinancial.entities.Category;
import com.personalfinancial.entities.Expenses;
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

	public String getAuthenticatedUserId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}

	public void saveExpenses(ExpensesDTO expensesDTO) {

		String authenticationId = getAuthenticatedUserId();

		UsersFinancial findUserId = usersFinancialRepository.findEntityByEmail(authenticationId)
				.orElseThrow(() -> new IllegalArgumentException("User not found with email" + authenticationId));

		Optional<Category> findCategory = categoryRepository.findById(expensesDTO.getCategoryId());

		if (findCategory.isPresent() && findUserId.isEnabled()) {
			Expenses expenses = new Expenses();
			expenses.setAmount(expensesDTO.getAmount());
			expenses.setDateHourFinancial(LocalDateTime.now());
			expenses.setDescription(expensesDTO.getDescription());
			expenses.setPaymentMethod(expensesDTO.getPaymentMethod());
			expenses.setUsersFinancial(findUserId);
			expenses.setCategory(findCategory.get());

			expensesRepository.save(expenses);
		} else {

			if (!findUserId.isEnabled()) {
				throw new IllegalArgumentException("User does not exist");
			}
			if (!findCategory.isPresent()) {
				throw new IllegalArgumentException("Category does not exist");
			}
		}
	}

	public void updateExpenses(Long id, ExpensesDTO expensesDTO) {

		String authenticationId = getAuthenticatedUserId();

		UsersFinancial findUserId = usersFinancialRepository.findEntityByEmail(authenticationId)
				.orElseThrow(() -> new IllegalArgumentException("User not found with email" + authenticationId));

		Optional<Expenses> findExpensesId = expensesRepository.findById(id);
		Optional<Category> findCategoryId = categoryRepository.findById(expensesDTO.getCategoryId());

		if (findExpensesId.isPresent() && findUserId != null && findCategoryId.isPresent()) {

			Expenses expenses = new Expenses();
			expenses.setAmount(expensesDTO.getAmount());
			expenses.setDateHourFinancial(LocalDateTime.now());
			expenses.setDescription(expensesDTO.getDescription());
			expenses.setPaymentMethod(expensesDTO.getPaymentMethod());
			expenses.setUsersFinancial(findUserId);
			expenses.setCategory(findCategoryId.get());

			expensesRepository.save(findExpensesId.get());

		}

	}

	public ExpensesDTO getExpensesUn(Long id) {

		String authenticationId = getAuthenticatedUserId();

		UsersFinancial findUserId = usersFinancialRepository.findEntityByEmail(authenticationId)
				.orElseThrow(() -> new IllegalArgumentException("User not found with email " + authenticationId));

		Optional<Expenses> expenses = expensesRepository.findById(id);

		if (findUserId.isEnabled() && expenses.isPresent()) {
			Expenses exp = expenses.get();
			ExpensesDTO expensesDTO = new ExpensesDTO();

			expensesDTO.setAmount(exp.getAmount());
			expensesDTO.setDescription(exp.getDescription());
			expensesDTO.setDateHourFinancial(exp.getDateHourFinancial().toString());
			expensesDTO.setPaymentMethod(exp.getPaymentMethod());
			expensesDTO.setCategoryId(exp.getCategory().getId());
			expensesDTO.setUsersFinancialId(exp.getId());
			expensesDTO.setId(exp.getId());

			return expensesDTO;

		} else {
			return null;
		}

	}

	public List<ExpensesDTO> getExpenses() {

		String authenticationId = getAuthenticatedUserId();

		UsersFinancial findUserId = usersFinancialRepository.findEntityByEmail(authenticationId)
				.orElseThrow(() -> new IllegalArgumentException("User not found with email " + authenticationId));

		List<Expenses> expenses = expensesRepository.findByUsersFinancial_Id(findUserId.getId());

		List<ExpensesDTO> expensesDTOs = new ArrayList<>();

		if (findUserId != null) {

			for (Expenses exp : expenses) {

				ExpensesDTO expensesDTO = new ExpensesDTO();

				expensesDTO.setAmount(exp.getAmount());
				expensesDTO.setDescription(exp.getDescription());
				expensesDTO.setDateHourFinancial(exp.getDateHourFinancial().toString());
				expensesDTO.setPaymentMethod(exp.getPaymentMethod());
				expensesDTO.setCategoryId(exp.getCategory().getId());
				expensesDTO.setUsersFinancialId(findUserId.getId());
				expensesDTO.setId(exp.getId());
				expensesDTOs.add(expensesDTO);

			}

		}
		if (!expensesDTOs.isEmpty()) {
			return expensesDTOs;
		} else {
			return null;
		}

	}

	public void deleteExpenses(Long id) {
		expensesRepository.deleteById(id);
	}

}
