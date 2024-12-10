package com.personalfinancial.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.personalfinancial.dto.RevenuesDTO;
import com.personalfinancial.entities.Category;
import com.personalfinancial.entities.Revenues;
import com.personalfinancial.entities.UsersFinancial;
import com.personalfinancial.repositories.CategoryRepository;
import com.personalfinancial.repositories.RevenuesRepository;
import com.personalfinancial.repositories.UsersFinancialRepository;

@Service
public class RevenuesService {

	@Autowired
	private RevenuesRepository revenuesRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private UsersFinancialRepository usersFinancialRepository;

	public String getAuthenticatedUserId() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}

	public void saveRevenues(RevenuesDTO revenuesDTO) {

		String authenticatedUserId = getAuthenticatedUserId();

		UsersFinancial findUsersId = usersFinancialRepository.findEntityByEmail(authenticatedUserId)
				.orElseThrow(() -> new IllegalArgumentException("User not found with email: " + authenticatedUserId));

		Optional<Category> findCategoryId = categoryRepository.findById(revenuesDTO.getCategoryId());

		if (findUsersId.isEnabled() && findCategoryId.isPresent()) {
			Revenues revenues = new Revenues();
			revenues.setAmount(revenuesDTO.getAmount());
			revenues.setDateHourFinancial(LocalDateTime.now());
			revenues.setDescription(revenuesDTO.getDescription());
			revenues.setPaymentMethod(revenuesDTO.getPaymentMethod());
			revenues.setUsersFinancial(findUsersId);
			revenues.setCategory(findCategoryId.get());

			revenuesRepository.save(revenues);
		} else {

			if (!findUsersId.isEnabled()) {
				throw new IllegalArgumentException("User does not exist");
			}
			if (!findCategoryId.isPresent()) {
				throw new IllegalArgumentException("Category does not exist");
			}
		}
	}

	public void updateRevenues(Long id, RevenuesDTO revenuesDTO) {

		String authenticatedUserId = getAuthenticatedUserId();

		UsersFinancial findUsersId = usersFinancialRepository.findEntityByEmail(authenticatedUserId)
				.orElseThrow(() -> new IllegalArgumentException("User not found with email: " + authenticatedUserId));

		Optional<Revenues> findRevenuesId = revenuesRepository.findById(id);
		Optional<Category> findCategoryId = categoryRepository.findById(revenuesDTO.getCategoryId());

		if (findRevenuesId.isPresent() && findUsersId != null && findCategoryId.isPresent()) {

			Revenues revenues = findRevenuesId.get();
			revenues.setAmount(revenuesDTO.getAmount());
			revenues.setDateHourFinancial(LocalDateTime.now());
			revenues.setDescription(revenuesDTO.getDescription());
			revenues.setPaymentMethod(revenuesDTO.getPaymentMethod());
			revenues.setUsersFinancial(findUsersId);
			revenues.setCategory(findCategoryId.get());

			revenuesRepository.save(revenues);

		}

	}

	public RevenuesDTO getRevenuesUn(Long id) {

		String authenticatedUserId = getAuthenticatedUserId();

		UsersFinancial findUsersId = usersFinancialRepository.findEntityByEmail(authenticatedUserId)
				.orElseThrow(() -> new IllegalArgumentException("User not found with email: " + authenticatedUserId));

		Optional<Revenues> findRevenuesId = revenuesRepository.findById(id);

		RevenuesDTO revenuesDTO = new RevenuesDTO();

		if (findRevenuesId.isPresent() && findUsersId.isEnabled()) {

			Revenues revenues = findRevenuesId.get();

			revenuesDTO.setAmount(revenues.getAmount());
			revenuesDTO.setCategoryId(revenues.getCategory().getId());
			revenuesDTO.setDateHourFinancial(revenues.getDateHourFinancial().toString());
			revenuesDTO.setDescription(revenues.getDescription());
			revenuesDTO.setPaymentMethod(revenues.getPaymentMethod());
			revenuesDTO.setUsersFinancialId(revenues.getUsersFinancial().getId());
			revenuesDTO.setId(id);

		}

		if (revenuesDTO.getDescription() == null) {
			return null;
		} else {
			return revenuesDTO;
		}

	}

	public List<RevenuesDTO> getRevenues() {

		String authenticatedUserId = getAuthenticatedUserId();

		UsersFinancial findUsersId = usersFinancialRepository.findEntityByEmail(authenticatedUserId)
				.orElseThrow(() -> new IllegalArgumentException("User not found with email: " + authenticatedUserId));

		List<Revenues> findRevenuesWithUserId = revenuesRepository.findByUsersFinancial_Id(findUsersId.getId());

		List<RevenuesDTO> revenuesDTOs = new ArrayList<>();

		for (Revenues revenues : findRevenuesWithUserId) {

			RevenuesDTO revenuesDTO = new RevenuesDTO();
			revenuesDTO.setAmount(revenues.getAmount());
			revenuesDTO.setDescription(revenues.getDescription());
			revenuesDTO.setDateHourFinancial(revenues.getDateHourFinancial().toString());
			revenuesDTO.setPaymentMethod(revenues.getPaymentMethod());
			revenuesDTO.setCategoryId(revenues.getCategory().getId());
			revenuesDTO.setUsersFinancialId(revenues.getUsersFinancial().getId());
			revenuesDTOs.add(revenuesDTO);

		}

		return revenuesDTOs;

	}

	public void deleteRevenues(Long id) {
		revenuesRepository.deleteById(id);
	}

}
