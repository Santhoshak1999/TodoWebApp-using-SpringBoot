package com.WebProject.todo.Todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Service;

@Service
public class TodoService {

	
	private static List<Todo> todos = new ArrayList<Todo>();
	private static int count = 0;
	static {
		todos.add(new Todo(count++, "in28minutes","Learn AWS", LocalDate.now().plusMonths(5), false));
		todos.add(new Todo(count++, "KSKS Hostel","StayInHostel", LocalDate.now().plusMonths(4), true));
		todos.add(new Todo(count++, "in28minutes","Learn Devops", LocalDate.now().plusMonths(5), false));
		todos.add(new Todo(count++, "in28minutes","FullStackDev", LocalDate.now().plusMonths(11), false));
	}
	
	public List<Todo> findByUsername(String username){
//		List<Todo> usertodos= new ArrayList<Todo>();
//		for(Todo todo : todos) {
//			if(todo.getName().equals(username)) {
//				usertodos.add(todo);
//			}
//		}
		
//		Predicate<? super Todo> predicate = todo -> todo.getName().equals(username);
		Predicate<? super Todo> predicate = todo -> todo.getName() != null && todo.getName().equals(username);

		todos.stream().filter(predicate).toList();
		return todos;
	}
	
	public void addTodos(String username, String description, LocalDate targetDate, boolean isDone) {
		Todo todo = new Todo(count++, username, description, targetDate, isDone);
		todos.add(todo);
	}
	
	
	public void deleteById(int id) {
		 Iterator<Todo> iterator = todos.iterator();
		    while (iterator.hasNext()) {
		        Todo todo = iterator.next();
		        if (todo.getId() == id) {
		            iterator.remove(); // Safely removes the item during iteration
		            break; // Exit after finding the item to delete
		        }
		    }
	}

	public Todo findById(int id) {
		// TODO Auto-generated method stub
		for(Todo todo : todos) {
			if(todo.getId() == id) {
				return todo;
			}
		}
		return null;
		
	}
	

}
