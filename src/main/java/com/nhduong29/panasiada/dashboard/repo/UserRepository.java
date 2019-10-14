package com.nhduong29.panasiada.dashboard.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nhduong29.panasiada.dashboard.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findByIdIn(List<Long> userIds);

	Optional<User> findByUsername(String username);

	boolean existsByUsername(String username);
}
