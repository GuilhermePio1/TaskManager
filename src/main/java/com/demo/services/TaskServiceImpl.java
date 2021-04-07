package com.demo.services;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.demo.models.Task;
import com.demo.repositories.TaskRepository;

@Transactional
public class TaskServiceImpl implements TaskService {
	private TaskRepository taskRepository;

	public TaskServiceImpl(TaskRepository taskRepository) {
		this.taskRepository = taskRepository;
	}

	@Override
	public List<Task> findAll() {
		return taskRepository.findAll();
	}

	@Override
	public Task find(long id) {
		return taskRepository.find(id);
	}

	@Override
	public void create(Task task) {
		taskRepository.create(task);
	}

	@Override
	public void update(Task task) {
		taskRepository.update(task);
	}

	@Override
	public void delete(Task task) {
		taskRepository.delete(task);
	}

	@Override
	public long LastId() {
		return taskRepository.getLastId();
	}

}
