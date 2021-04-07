package com.demo.services;

import java.util.List;

import com.demo.models.Task;

public interface TaskService {
	public List<Task> findAll();
	public Task find(long id);
	public void create(Task task);
	public void update(Task task);
	public void delete(Task task);
	public long LastId();
}
