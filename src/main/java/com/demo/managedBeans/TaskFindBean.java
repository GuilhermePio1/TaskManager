package com.demo.managedBeans;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.exceptions.DateInThePastException;
import com.demo.exceptions.StringIsEmptyException;
import com.demo.models.Priority;
import com.demo.models.Situation;
import com.demo.models.Task;
import com.demo.services.TaskService;

@Component
@ManagedBean(name = "taskFindBean")
@SessionScoped
public class TaskFindBean {
	@Autowired
	public TaskService taskService;

	private List<Task> tasks;
	private Task researchedTask;
	private List<Task> allTasks;
	private Task editTask;
	private UIComponent button;

	@PostConstruct
	public void onload() {
		this.allTasks = taskService.findAll();
		this.tasks = allTasks;

		this.researchedTask = new Task();
	}

	public List<Task> getTasks() {
		return tasks;
	}

	public void setTasks(List<Task> tasks) {
		this.tasks = tasks;
	}

	public Task getResearchedTask() {
		return researchedTask;
	}

	public void setResearchedTask(Task researchedTask) {
		this.researchedTask = researchedTask;
	}

	public Situation[] getSituations() {
		return Situation.values();
	}

	public void searchTasks() {
		List<Task> researchedTasks = new ArrayList<Task>();

		for (Task task : allTasks) {
			if (idIsEqual(task.getId())) {
				researchedTasks.add(task);
				this.tasks = researchedTasks;
				this.allTasks = taskService.findAll();
				return;
			}
		}

		for (Task task : allTasks) {
			if (situationIsEqual(task.getSituation().name())) {
				researchedTasks.add(task);
			} else if (responsibleIsEqual(task.getResponsible())) {
				researchedTasks.add(task);
			} else if (titleDescriptionIsEqual(task)) {
				researchedTasks.add(task);
			}
		}

		this.tasks = researchedTasks;
		this.allTasks = taskService.findAll();
	}

	private boolean isEmpty(String string) {
		if (string.isBlank() || string == null)
			return true;

		return false;
	}

	private boolean idIsEqual(long id) {
		if (researchedTask.getId() == id)
			return true;

		return false;
	}

	private boolean situationIsEqual(String situation) {
		if (researchedTask.getSituation().name().equalsIgnoreCase(situation))
			return true;

		return false;
	}

	private boolean responsibleIsEqual(String responsible) {
		if (!(isEmpty(researchedTask.getResponsible())))
			if (researchedTask.getResponsible().equalsIgnoreCase(responsible))
				return true;

		return false;
	}

	private boolean titleDescriptionIsEqual(Task task) {
		if (!(isEmpty(researchedTask.getTitle())))
			if (researchedTask.getTitle().equalsIgnoreCase(task.getTitle())) {
				return true;
			} else {
				List<String> wordsResearchedTask = Arrays.asList(researchedTask.getTitle().split(" "));
				List<String> wordsTask = Arrays.asList(task.getDescription().split(" "));
				boolean result = wordsResearchedTask.stream().anyMatch(word -> {
					return wordsTask.stream().anyMatch(word2 -> {
						return word.equalsIgnoreCase(word2);
					});
				});

				return result;
			}

		return false;
	}

	public String update(Task task) {
		this.editTask = task;
		return "editTask?faces-redirect=true";
	}

	public Task getEditTask() {
		return editTask;
	}

	public void setEditTask(Task editTask) {
		this.editTask = editTask;
	}

	public Priority[] getPriorities() {
		return Priority.values();
	}

	public String edit() {
		try {
			this.taskService.update(this.editTask);
			return "index?faces-redirect=true";			
		}catch (StringIsEmptyException e) {
			e.printStackTrace();
			return null;
		}catch (DateInThePastException e) {
			FacesMessage message = new FacesMessage("Coloque uma data no futuro");
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(button.getClientId(context), message);
			return null;
		}
	}

	public String delete(Task task) {
		this.taskService.delete(task);
		this.tasks.remove(task);
		return "taskListing?faces-redirect=true";
	}

	public String completed(Task task) {
		task.setSituation(Situation.Concluída);
		this.taskService.update(task);
		this.allTasks = this.taskService.findAll();
		return "taskListing?faces-redirect=true";
	}

	public UIComponent getButton() {
		return button;
	}

	public void setButton(UIComponent button) {
		this.button = button;
	}

}
