package com.app.org.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.app.org.entity.AppUser;

public interface UserRepository extends JpaRepository<AppUser, Long> {
   public AppUser findByUsername(String username);
}
