package com.WebProject.todo.Todo;

import java.time.LocalDate;

import org.springframework.stereotype.Component;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

//to Store in a database SQL or 



@Component
@Entity(name = "todos") //mapping to database and automatically calls creating table in h2
public class Todo {

	@Id //primary key for id
	@GeneratedValue
	private int id;
	private String name;
	@Size(min = 10, message="Enter alteast 10 characters")
	private String description;
	private LocalDate targetDate; 
	@NotNull(message = "Please insert only boolean(True/False) values")
	private Boolean done;
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getTargetDate() {
		return targetDate;
	}

	public void setTargetDate(LocalDate targetDate) {
		this.targetDate = targetDate;
	}

	public Boolean getDone() {
		return done;
	}

	public void setDone(Boolean done) {
		this.done = done;
	}

	public Todo(int id, String name, String description, LocalDate targetDate, Boolean done) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.targetDate = targetDate;
		this.done = done;
	}

	@Override
	public String toString() {
		return "Todo [id=" + id + ", name=" + name + ", description=" + description + ", targetDate=" + targetDate
				+ ", done=" + done + "]";
	}

	public Todo() {
		super();
	}

}
