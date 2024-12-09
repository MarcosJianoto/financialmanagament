package com.personalfinancial.services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
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

	public void saveRevenues(RevenuesDTO revenuesDTO) {

		Optional<UsersFinancial> findUsersId = usersFinancialRepository.findById(revenuesDTO.getUsersFinancialId());
		Optional<Category> findCategoryId = categoryRepository.findById(revenuesDTO.getCategoryId());

		if (findUsersId.isPresent() && findCategoryId.isPresent()) {
			Revenues revenues = new Revenues();
			revenues.setAmount(revenuesDTO.getAmount());
			revenues.setDateHourFinancial(LocalDateTime.now());
			revenues.setDescription(revenuesDTO.getDescription());
			revenues.setPaymentMethod(revenuesDTO.getPaymentMethod());
			revenues.setUsersFinancial(findUsersId.get());
			revenues.setCategory(findCategoryId.get());

			revenuesRepository.save(revenues);
		} else {
			throw new IllegalArgumentException("User is not exist or Category is not exist");
		}
	}

	public void updateRevenues(Long id, RevenuesDTO revenuesDTO) {

		Optional<Revenues> findRevenuesId = revenuesRepository.findById(id);
		Optional<UsersFinancial> findUserId = usersFinancialRepository.findById(revenuesDTO.getUsersFinancialId());
		Optional<Category> findCategoryId = categoryRepository.findById(revenuesDTO.getCategoryId());

		if (findRevenuesId.isPresent() && findUserId.isPresent() && findCategoryId.isPresent()) {

			Revenues revenues = new Revenues();
			revenues.setAmount(revenuesDTO.getAmount());
			revenues.setDateHourFinancial(LocalDateTime.now());
			revenues.setDescription(revenuesDTO.getDescription());
			revenues.setPaymentMethod(revenuesDTO.getPaymentMethod());
			revenues.setUsersFinancial(findUserId.get());
			revenues.setCategory(findCategoryId.get());

			revenuesRepository.save(findRevenuesId.get());

		}

	}

	public List<RevenuesDTO> getRevenues(Long id) {

		List<Revenues> findUserId = revenuesRepository.findByUsersFinancial_Id(id);
		List<RevenuesDTO> revenuesDTOs = new ArrayList<>();

		for (Revenues revenues : findUserId) {

			RevenuesDTO revenuesDTO = new RevenuesDTO();
			revenuesDTO.setAmount(revenues.getAmount());
			revenuesDTO.setDescription(revenues.getDescription());
			revenuesDTO.setDateHourFinancial(revenues.getDateHourFinancial().toString());
			revenuesDTO.setPaymentMethod(revenues.getPaymentMethod());
			revenuesDTO.setCategoryId(revenues.getCategory().getId());
			revenuesDTO.setUsersFinancialId(revenues.getId());
			revenuesDTOs.add(revenuesDTO);

		}

		return revenuesDTOs;

	}

	public void deleteRevenues(Long id) {
		revenuesRepository.deleteById(id);
	}

}
