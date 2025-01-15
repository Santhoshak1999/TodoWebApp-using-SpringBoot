package com.WebProject.todo.Todo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository												//Wrapper class for integer
public interface TodoRepository extends JpaRepository<Todo, Integer>{

	List<Todo> findByName(String name);
	
}
