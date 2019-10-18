package com.nhduong29.panasiada.dashboard.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import com.nhduong29.panasiada.dashboard.entity.Car;

@Repository
public interface CarRepository extends PagingAndSortingRepository<Car, Long> {
	Page<Car> findByBrand(String brand, Pageable pageable);

	@Query(value = "SELECT * FROM Car car WHERE car.brand=:brand AND car.color=:color", nativeQuery = true)
	Page<Car> filterByBrandAndColor(String brand, String color, Pageable pageable);
}
