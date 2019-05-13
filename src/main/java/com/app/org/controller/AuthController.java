package com.app.org.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.authentication.AuthenticationManagerBeanDefinitionParser;
import org.springframework.security.config.authentication.AuthenticationManagerFactoryBean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.org.jwt.JwtProvider;
import com.app.org.jwt.JwtResponse;
import com.app.org.jwt.LoginForm;
import com.app.org.repository.RoleRepository;
import com.app.org.repository.UserRepository;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class AuthController {

	
	
	@Autowired
	AuthenticationManager authenticationManager;
 
	@Autowired
	UserRepository userRepository;
 
	@Autowired
	RoleRepository roleRepository;
 
	@Autowired
	PasswordEncoder encoder;
 
	@Autowired
	JwtProvider jwtProvider;
 
	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginForm loginRequest) {
 
		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
 
		SecurityContextHolder.getContext().setAuthentication(authentication);
 
		String jwt = jwtProvider.generateJwtToken(authentication);
		UserDetails userDetails = (UserDetails) authentication.getPrincipal();
 
		return ResponseEntity.ok(new JwtResponse(jwt, userDetails.getUsername(), userDetails.getAuthorities()));
	}
}
