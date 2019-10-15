package com.nhduong29.panasiada.dashboard.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.nhduong29.panasiada.dashboard.enums.BrandEnum;

@Entity
@Table(name = "car")
public class Car {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotBlank
	@Size(max = 4)
	private int year;

	@Enumerated(EnumType.STRING)
	@Column(length = 40)
	private BrandEnum brand;

	@NotBlank
	@Column(length = 40)
	private String color;

	@NotBlank
	@Column(length = 100)
	private String model;

	public Car() {
		super();
	}

	public Car(Long id, int year, BrandEnum brand, String color, String model) {
		super();
		this.id = id;
		this.year = year;
		this.brand = brand;
		this.color = color;
		this.model = model;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public BrandEnum getBrand() {
		return brand;
	}

	public void setBrand(BrandEnum brand) {
		this.brand = brand;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

}
