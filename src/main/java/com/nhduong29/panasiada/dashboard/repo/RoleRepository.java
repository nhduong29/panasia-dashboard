package com.nhduong29.panasiada.dashboard.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nhduong29.panasiada.dashboard.entity.Role;
import com.nhduong29.panasiada.dashboard.enums.RoleEnum;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
	Optional<Role> findByName(RoleEnum roleName);
}