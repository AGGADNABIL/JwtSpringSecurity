package com.app.org.service;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.app.org.entity.AppUser;
import com.app.org.repository.UserRepository;

@Service
public class UserSecurityService implements UserDetailsService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		AppUser user= userRepository.findByUsername(username);
		if(user==null) throw new UsernameNotFoundException(" Utilisateur n'existe pas :"+username);
		Collection<GrantedAuthority> authorities=user.getRoles()
													 .stream()
													 .map(r -> new SimpleGrantedAuthority(r.getRolename()))
													 .collect(Collectors.toList());
		return new User(user.getUsername(),user.getPassword(),authorities);
	}

	
	

}
