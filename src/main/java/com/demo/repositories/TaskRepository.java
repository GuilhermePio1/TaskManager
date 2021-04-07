package com.demo.repositories;

import java.util.List;

import com.demo.models.Task;

public interface TaskRepository {
	public List<Task> findAll();
	public Task find(long id);
	public void create(Task task);
	public void update(Task task);
	public void delete(Task task);
	public long getLastId();
}
