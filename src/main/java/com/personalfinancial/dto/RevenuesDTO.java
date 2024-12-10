package com.personalfinancial.dto;

import com.personalfinancial.entities.PaymentMethod;

public class RevenuesDTO {

	private Long id;

	private Long categoryId;

	private Long usersFinancialId;

	private String description;

	private Double amount;

	private String dateHourFinancial;

	private PaymentMethod paymentMethod;

	public RevenuesDTO() {
	}

	public RevenuesDTO(Long id, Long categoryId, Long usersFinancialId, String description, Double amount,
			String dateHourFinancial, PaymentMethod paymentMethod) {
		this.id = id;
		this.categoryId = categoryId;
		this.usersFinancialId = usersFinancialId;
		this.description = description;
		this.amount = amount;
		this.dateHourFinancial = dateHourFinancial;
		this.paymentMethod = paymentMethod;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(Long categoryId) {
		this.categoryId = categoryId;
	}

	public Long getUsersFinancialId() {
		return usersFinancialId;
	}

	public void setUsersFinancialId(Long usersFinancialId) {
		this.usersFinancialId = usersFinancialId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public String getDateHourFinancial() {
		return dateHourFinancial;
	}

	public void setDateHourFinancial(String dateHourFinancial) {
		this.dateHourFinancial = dateHourFinancial;
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}
}
