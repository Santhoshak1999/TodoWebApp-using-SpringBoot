package com.WebProject.todo.Todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

//@Controller
@SessionAttributes("name")
public class TodoController {
	
	@Autowired
    private HttpSession session;

	
	private TodoService todoService;
	//Construtor Injection needs autowired anotations
	
	private TodoRepository todorepository;
	@Autowired
	public TodoController(TodoService todoService, TodoRepository todoRepository) {
		super();
		this.todoService = todoService;
		this.todorepository = todoRepository;
	}

	@RequestMapping(value="list-todos")
	public String listAllTodos(ModelMap model) {
		
		String username = getLoggedinUsername();
		List<Todo> todos = todorepository.findByName("username");
		model.put("todos", todos);
		return "listTodos";
	}

//	Get the current Username from authentication details
	private String getLoggedinUsername() {
//		Access the Current User's Details:
//		authentication object, which contains details about the currently authenticated user
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 
//		get user name from the authentication details
		return authentication.getClass().getName();
	}

	public TodoController() {
		super();
	}
	
	//By Default it Handle All Methods
	@RequestMapping(value="add-todo", method=RequestMethod.GET)
	public String addNewTodos(ModelMap modelmap) {
		String username = (String)session.getAttribute("username");			//you can use this modelmap to fetch the username that maked session scope
		//Two way binding
		modelmap.addAttribute("todo", new Todo(0, username, "Deafault Desc", LocalDate.now().plusYears(1), false));
		return "addNewTodo";
	}
	
	//return listTodo after added a todo to list
	@RequestMapping(value="add-todo", method=RequestMethod.POST)
	public String CompletedTodo(ModelMap modelmap, @Valid Todo todo, BindingResult result) {
		if(result.hasErrors()) {
			//error will be displayed on input:error
			return "addNewTodo";
		}
		String username = (String)session.getAttribute("username");			//you can use this modelmap to fetch the username that maked session scope
		todoService.addTodos(username, todo.getDescription(), todo.getTargetDate(), todo.getDone());
		//return with the url use "redirect" keyword
		return "redirect:list-todos";
	}
	
	//Method to Delete a Todo
	@RequestMapping(value="delete-todo", method=RequestMethod.GET)
	public String deleteTodo(@RequestParam int id) {
		todoService.deleteById(id);
		return "redirect:list-todos";
	}
	
	//Method to Update Todos
	@RequestMapping(value="/update-todo", method=RequestMethod.GET)
	public String showupdateTodoPage(@RequestParam int id, ModelMap modelmap) {
		Todo todo = todoService.findById(id);
//		todo.setDescription(null);
		modelmap.addAttribute("todo", todo);
		return "addNewTodo";
	}
	
	@RequestMapping(value="/update-todo", method=RequestMethod.POST)
	public String updateTodo(@RequestParam int id, @Valid Todo uptodo, BindingResult result) {
		if(result.hasErrors()) {
			return "addNewTodo";			
		}
		Todo todo = todoService.findById(uptodo.getId());
		todo.setDescription(uptodo.getDescription());
		todo.setTargetDate(uptodo.getTargetDate());
		todo.setDone(uptodo.getDone());
		return "redirect:list-todos";
	}
	
	
	
}
