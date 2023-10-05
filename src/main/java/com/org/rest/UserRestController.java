package com.org.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.org.dto.RegistrationRequest;
import com.org.service.AdminServiceImpl;
import com.org.service.UserServiceImpl;

@RestController
@RequestMapping("/user")
@Valid
public class UserRestController {

	@Autowired
	private AdminServiceImpl adminServiceImpl;

	@Autowired
	private UserServiceImpl userServiceImpl;

	@GetMapping("/login/{mail}")
	public ResponseEntity<?> loginUser(@Valid @PathVariable String mail) {
		UserDetails loadUserByUsername = adminServiceImpl.loadUserByUsername(mail);
		return new ResponseEntity<>(loadUserByUsername, HttpStatus.OK);
	}

	@GetMapping("/getPorifile/{id}")
	public ResponseEntity<?> getProfileDtls(@Valid @PathVariable Integer id) {
		RegistrationRequest userDto = userServiceImpl.getUserById(id);
		return new ResponseEntity<>(userDto, HttpStatus.OK);
	}

	@PutMapping("/getPorifile/edit/{id}")
	public ResponseEntity<?> editProfileDtls(@Valid @PathVariable Integer id,
			@RequestBody RegistrationRequest request) {
		String updateUserDtls = userServiceImpl.updateUserDtls(id, request);
		return new ResponseEntity<>(updateUserDtls, HttpStatus.OK);
	}

}
