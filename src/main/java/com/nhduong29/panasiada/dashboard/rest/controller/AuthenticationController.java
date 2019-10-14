package com.nhduong29.panasiada.dashboard.rest.controller;

import java.net.URI;
import java.util.Collections;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.nhduong29.panasiada.dashboard.config.security.jwt.JwtTokenProvider;
import com.nhduong29.panasiada.dashboard.entity.Role;
import com.nhduong29.panasiada.dashboard.entity.User;
import com.nhduong29.panasiada.dashboard.enums.RoleEnum;
import com.nhduong29.panasiada.dashboard.repo.RoleRepository;
import com.nhduong29.panasiada.dashboard.repo.UserRepository;
import com.nhduong29.panasiada.dashboard.rest.exception.GeneralException;
import com.nhduong29.panasiada.dashboard.rest.request.LoginRequest;
import com.nhduong29.panasiada.dashboard.rest.request.SignUpRequest;
import com.nhduong29.panasiada.dashboard.rest.response.GeneralResponse;
import com.nhduong29.panasiada.dashboard.rest.response.JwtAuthenticationResponse;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	PasswordEncoder passwordEncoder;

	@Autowired
	JwtTokenProvider jwtTokenProvider;

	@Autowired
	UserRepository userRepo;

	@Autowired
	RoleRepository roleRepo;

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignUpRequest signUpRequest) {
		if (userRepo.existsByUsername(signUpRequest.getUsername())) {
			return new ResponseEntity<GeneralResponse>(
					new GeneralResponse(false, "Username is exits, Please use another one!"), HttpStatus.BAD_REQUEST);
		}
		User user = new User(signUpRequest.getName(), signUpRequest.getUsername(), signUpRequest.getEmail(),
				signUpRequest.getPassword());
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		Role userRole = roleRepo.findByName(RoleEnum.ROLE_USER)
				.orElseThrow(() -> new GeneralException("User Role not set."));
		user.setRoles(Collections.singleton(userRole));
		User savedUser = userRepo.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/users/{username}")
				.buildAndExpand(savedUser.getUsername()).toUri();
		return ResponseEntity.created(location).body(new GeneralResponse(true, "User registered successfully!!!"));
	}

	@PostMapping("/signin")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtTokenProvider.generateToken(authentication);
		return ResponseEntity.ok(new JwtAuthenticationResponse(jwt));
	}
}
