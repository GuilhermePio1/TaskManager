package com.demo.repositories;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.demo.exceptions.DateInThePastException;
import com.demo.exceptions.StringIsEmptyException;
import com.demo.models.Task;

public class TaskRepositoryImpl implements TaskRepository {
	@PersistenceContext
	private EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<Task> findAll() {
		try {
			return entityManager.createQuery("from Task").getResultList();
		}catch (Exception e) {
			return null;
		}
	}

	@Override
	public Task find(long id) {
		try {
			return (Task) entityManager.createQuery("from Task where id = :id").setParameter("id", id).getSingleResult();
		}catch (Exception e) {
			return null;
		}
	}

	@Override
	public void create(Task task) throws StringIsEmptyException, DateInThePastException {
		if (isEmpty(task.getTitle()) || isEmpty(task.getResponsible())) {
			throw new StringIsEmptyException("Título ou Responsável esta vazio");
		}
		
		boolean date = new Date().before(task.getDeadline());
		if (!date) {
			throw new DateInThePastException("Data está no passado");
		}
		
		entityManager.persist(task);
	}
	
	@Override
	public void update(Task task) throws StringIsEmptyException, DateInThePastException {
		if (isEmpty(task.getTitle()) || isEmpty(task.getResponsible())) {
			throw new StringIsEmptyException("Título ou Responsável esta vazio");
		}
		
		boolean date = new Date().before(task.getDeadline());
		if (!date) {
			throw new DateInThePastException("Data está no passado");
		}
		
		entityManager.merge(task);
	}
	
	@Override
	public void delete(Task task) {
		entityManager.remove(entityManager.merge(task));
	}

	@Override
	public long getLastId() {
		try {
			return (long) entityManager.createQuery("select max(t.id) from Task t").getSingleResult() + 1;
		}catch(Exception e) {
			return 1;
		}
	}

	private boolean isEmpty(String string) {
		if (string.isBlank() || string == null)
			return true;
		
		return false;
	}
}
