package com.app.org;

import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.app.org.entity.AppUser;
import com.app.org.entity.Role;
import com.app.org.entity.Task;
import com.app.org.repository.TaskRepsitory;
import com.app.org.service.MyUserAppService;

@SpringBootApplication
public class SpringSecurityJwtApplication implements CommandLineRunner {

	@Autowired
	private TaskRepsitory taskRepsitory;
	
	@Autowired
	private MyUserAppService userService;
	
	
	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityJwtApplication.class, args);
		//https://grokonez.com/spring-framework/spring-boot/angular-spring-boot-jwt-authentication-example-angular-6-spring-security-mysql-full-stack-part-2-build-backend
		//
	}

	
	
	@Override
	public void run(String... args) throws Exception {
		if(taskRepsitory.findAll().isEmpty())
		Stream.of("task1","task2","task3").forEach(s -> taskRepsitory.save(new Task(null, s)));
		if(userService.findAllUser().isEmpty())
		Stream.of("admin","nabil","user").forEach(u -> userService.saveUser(new AppUser(u, u)));
		if(userService.findAllRole().isEmpty()) {
			Stream.of("ADMIN","USER").forEach(u -> userService.saveRole(new Role(u)));
			userService.affecterRoleToUser("admin", "ADMIN");
			userService.affecterRoleToUser("admin", "USER");
			userService.affecterRoleToUser("user", "USER");
			userService.affecterRoleToUser("nabil", "USER");
		}
		
		
		
		
	}

}

