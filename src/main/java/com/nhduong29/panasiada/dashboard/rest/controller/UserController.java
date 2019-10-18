package com.nhduong29.panasiada.dashboard.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.nhduong29.panasiada.dashboard.config.security.CurrentUser;
import com.nhduong29.panasiada.dashboard.config.security.CustomUserDetail;
import com.nhduong29.panasiada.dashboard.entity.User;
import com.nhduong29.panasiada.dashboard.repo.UserRepository;
import com.nhduong29.panasiada.dashboard.rest.exception.GeneralException;
import com.nhduong29.panasiada.dashboard.rest.response.UserIdentityAvailability;
import com.nhduong29.panasiada.dashboard.rest.response.UserProfile;
import com.nhduong29.panasiada.dashboard.rest.response.UserSummaryResponse;

@RestController
@RequestMapping("/api")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping("/user/me")
	@PreAuthorize("hasRole('USER')")
	public UserSummaryResponse getCurrentUser(@CurrentUser CustomUserDetail currentUser) {
		UserSummaryResponse userSummary = new UserSummaryResponse(currentUser.getId(), currentUser.getUsername(),
				currentUser.getName());
		return userSummary;
	}

	@GetMapping("/user/checkUsernameAvailability")
	public UserIdentityAvailability checkUsernameAvailability(@RequestParam(value = "username") String username) {
		Boolean isAvailable = !userRepository.existsByUsername(username);
		return new UserIdentityAvailability(isAvailable);
	}

	@GetMapping("/users/{username}")
	public UserProfile getUserProfile(@PathVariable(value = "username") String username) {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new GeneralException("User with the username : " + username + " is not found"));

		UserProfile userProfile = new UserProfile(user.getId(), user.getUsername(), user.getName());

		return userProfile;
	}
}
