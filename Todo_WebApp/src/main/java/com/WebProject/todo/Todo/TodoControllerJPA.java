package com.WebProject.todo.Todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
@SessionAttributes("name")
public class TodoControllerJPA {
	
	@Autowired
    private HttpSession session;

	
	private TodoService todoService;
	//Construtor Injection needs autowired anotations
	@Autowired
	private TodoRepository todoRepository;
	
	@Autowired
	public TodoControllerJPA(TodoService todoService, TodoRepository todoRepository) {
		super();
		this.todoService = todoService;
		this.todoRepository= todoRepository;
	}

	@RequestMapping(value="list-todos")
	public String listAllTodos(ModelMap model) {
		
		String username = getLoggedinUsername(model);
		List<Todo> todos = todoRepository.findByName(username);;
		model.addAttribute("todos", todos);
		return "listTodos";
	}

//	Get the current Username from authentication details
	private String getLoggedinUsername(ModelMap model) {
//		Access the Current User's Details:
//		authentication object, which contains details about the currently authenticated user
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 
		if(authentication != null && authentication.getPrincipal() instanceof UserDetails) {
			return ((UserDetails) authentication.getPrincipal()).getUsername();
			//		get user name from the authentication details
		}
		return authentication.getClass().getName();
	}

	public TodoControllerJPA() {
		super();
	}
	
	//By Default it Handle All Methods
	@RequestMapping(value="add-todo", method=RequestMethod.GET)
	public String showNewTodoPage(ModelMap modelmap) {
		String username = (String)session.getAttribute("username");			//you can use this modelmap to fetch the username that maked session scope
		//Two way binding
		modelmap.put("todo", new Todo(0, username, "Deafault Desc", LocalDate.now().plusYears(1), false));
		return "addNewTodo";
	}
	
	//return listTodo after added a todo to list
	@RequestMapping(value="add-todo", method=RequestMethod.POST)
	public String addNewTodo(ModelMap modelmap, @Valid Todo todo, BindingResult result) {
		if(result.hasErrors()) {
			//error will be displayed on input:error
			System.out.println("Result" + result);
			return "addNewTodo";
		}
		String username = getLoggedinUsername(modelmap);		//you can use this modelmap to fetch the username that maked session scope
		todo.setName(username);
		todoRepository.save(todo);
		
		//		todoService.addTodos(username, todo.getDescription(), 
//				todo.getTargetDate(), todo.getDone());
		//return with the url use "redirect" keyword
		return "redirect:list-todos";
	}
	
	//Method to Delete a Todo
	@RequestMapping(value="delete-todo", method=RequestMethod.GET)
	public String deleteTodo(@RequestParam int id) {
		todoService.deleteById(id);
		Todo todo = todoRepository.findById(id).orElse(null);
		todoRepository.delete(todo);
		return "redirect:list-todos";
	}
	
	//Method to Update Todos
	@RequestMapping(value="/update-todo", method=RequestMethod.GET)
	public String showupdateTodoPage(@RequestParam int id, ModelMap modelmap) {
		Todo todo = todoRepository.findById(id).orElse(null);
//		todo.setDescription(null);
		modelmap.addAttribute("todo", todo);
		return "addNewTodo";
	} 
	
	@RequestMapping(value="/update-todo", method=RequestMethod.POST)
	public String updateTodo(@RequestParam int id, @Valid Todo uptodo, BindingResult result) {
		if(result.hasErrors()) {
			return "addNewTodo";			
		}
		Todo todo = todoRepository.findById(uptodo.getId()).orElse(null);
		todo.setDescription(uptodo.getDescription());
		todo.setTargetDate(uptodo.getTargetDate());
		todo.setDone(uptodo.getDone()); 
		todoRepository.save(todo);
		return "redirect:list-todos";
	}
	
	
	
}
