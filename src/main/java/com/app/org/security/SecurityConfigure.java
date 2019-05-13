package com.app.org.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.app.org.jwt.JwtAuthTokenFilter;
import com.app.org.jwt.JwtAuthenticationEntryPoint;

@Configuration
@EnableWebSecurity
public class SecurityConfigure  extends WebSecurityConfigurerAdapter{
  
	@Autowired
	private UserDetailsService userDetailsService;
	/*
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	*/
	
	@Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }
	
	@Autowired
    private JwtAuthenticationEntryPoint unauthorizedHandler;
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
    public JwtAuthTokenFilter authenticationJwtTokenFilter() {
        return new JwtAuthTokenFilter();
    }
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder());
		/*auth.inMemoryAuthentication()
			.withUser("admin").password(passwordEncoder().encode("admin")).roles("ADMIN")
			.and()
			.withUser("nabil").password(passwordEncoder().encode("nabil")).roles("USER");*/
		
	}
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable();
		//http.exceptionHandling().authenticationEntryPoint(unauthorizedHandler);
		//http.formLogin();
		    
		http.authorizeRequests().antMatchers("/login/**").permitAll()
			.and()
			.authorizeRequests().anyRequest().authenticated();
		
		http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(authenticationJwtTokenFilter(), UsernamePasswordAuthenticationFilter.class);
	}
	
}
