package com.demo.repositories;

import java.util.List;

import com.demo.exceptions.DateInThePastException;
import com.demo.exceptions.StringIsEmptyException;
import com.demo.models.Task;

public interface TaskRepository {
	public List<Task> findAll();
	public Task find(long id);
	public void create(Task task) throws DateInThePastException, StringIsEmptyException;
	public void update(Task task) throws DateInThePastException, StringIsEmptyException;
	public void delete(Task task);
	public long getLastId();
}
