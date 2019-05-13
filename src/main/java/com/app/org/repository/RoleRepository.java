package com.app.org.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.org.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Long>{
	Role findByRolename(String rolename);
}
