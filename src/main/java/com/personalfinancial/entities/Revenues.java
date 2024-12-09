package com.personalfinancial.entities;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table(name = "revenues")
public class Revenues {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "seq_revenues")
	@SequenceGenerator(name = "seq_revenues", sequenceName = "seq_revenues", allocationSize = 1)
	@Column(name = "id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category category;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private UsersFinancial usersFinancial;

	@Column(name = "description")
	private String description;

	@Column(name = "amount")
	private Double amount;

	@Column(name = "date_hour_financial")
	private LocalDateTime dateHourFinancial;

	@Column(name = "payment_method")
	@Enumerated(EnumType.STRING)
	private PaymentMethod paymentMethod;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public UsersFinancial getUsersFinancial() {
		return usersFinancial;
	}

	public void setUsersFinancial(UsersFinancial usersFinancial) {
		this.usersFinancial = usersFinancial;
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

	public LocalDateTime getDateHourFinancial() {
		return dateHourFinancial;
	}

	public void setDateHourFinancial(LocalDateTime dateHourFinancial) {
		this.dateHourFinancial = dateHourFinancial;
	}

	public PaymentMethod getPaymentMethod() {
		return paymentMethod;
	}

	public void setPaymentMethod(PaymentMethod paymentMethod) {
		this.paymentMethod = paymentMethod;
	}

}
