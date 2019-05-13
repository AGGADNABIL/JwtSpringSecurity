package com.app.org.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.app.org.entity.Task;


public interface TaskRepsitory  extends JpaRepository<Task, Long>{

}
