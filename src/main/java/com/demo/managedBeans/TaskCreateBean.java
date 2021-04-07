package com.demo.managedBeans;

import java.util.Date;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.demo.models.Priority;
import com.demo.models.Situation;
import com.demo.models.Task;
import com.demo.services.TaskService;

@Component
@ManagedBean(name = "taskCreateBean")
@SessionScoped
public class TaskCreateBean {
	@Autowired
	public TaskService taskService;

	private UIComponent button;

	private Task task;

	public void onload() {
		this.task = new Task();
	}

	public String save() {
		boolean date = new Date().before(this.task.getDeadline());
		if (!date) {
			FacesMessage message = new FacesMessage("Coloque uma data no futuro");
			FacesContext context = FacesContext.getCurrentInstance();
			context.addMessage(button.getClientId(context), message);
			
			return null;
		}
		
		this.task.setId(taskService.LastId());
		this.task.setSituation(Situation.emAndamento);
		this.taskService.create(task);
		this.task = new Task();
		return "index?faces-redirect=true";
	}

	public Task getTask() {
		return task;
	}

	public void setTask(Task task) {
		this.task = task;
	}

	public Priority[] getPriorities() {
		return Priority.values();
	}

	public UIComponent getButton() {
		return button;
	}

	public void setButton(UIComponent button) {
		this.button = button;
	}
	
}
