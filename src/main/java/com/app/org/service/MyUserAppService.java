package com.app.org.service;

import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.app.org.entity.AppUser;
import com.app.org.entity.Role;
import com.app.org.repository.RoleRepository;
import com.app.org.repository.UserRepository;

@Service
@Transactional
public class MyUserAppService {

	private Logger logger=LoggerFactory.getLogger(MyUserAppService.class);
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private RoleRepository roleRepository;
	
	/*
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;*/
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	public AppUser saveUser(AppUser user) {
		user.setPassword(passwordEncoder().encode(user.getPassword()));
		return userRepository.save(user);
	}
	
	public Role saveRole(Role role) {
		return roleRepository.save(role);
	}
	
	public void affecterRoleToUser(String username , String rolename) {
		AppUser user =userRepository.findByUsername(username);
		Role role=roleRepository.findByRolename(rolename);
		logger.info("role currant :"+role.getId()+" "+role.getRolename());
		user.getRoles().add(role);
	}
	
	public List<AppUser> findAllUser(){
		return userRepository.findAll();
	}
	public List<Role> findAllRole(){
		return roleRepository.findAll();
	}
}
