package com.nhduong29.panasiada.dashboard.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nhduong29.panasiada.dashboard.entity.Car;
import com.nhduong29.panasiada.dashboard.repo.CarRepository;

@RestController
@RequestMapping("/api/car")
public class CarController {
	@Autowired
	private CarRepository carRepository;

	@GetMapping("/allCars")
	public Page<Car> getAllArticles(Pageable pageable) {
		return carRepository.findAll(pageable);
	}

	@GetMapping("/{brand}")
	public Page<Car> getAllByCategory(@PathVariable String brand, Pageable pageable) {
		return carRepository.findByBrand(brand, pageable);
	}
}
