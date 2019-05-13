package com.app.org.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.app.org.entity.Task;
import com.app.org.repository.TaskRepsitory;

@RestController
public class TaskController {
	
	@Autowired
	private TaskRepsitory taskRepository;
	
	@GetMapping(value="/tasks")
	public List<Task> listTask(){
		return taskRepository.findAll();
	}

	@PostMapping(value="/tasks")
	public Task save(@RequestBody Task t) {
		return taskRepository.save(t);
	}
}
