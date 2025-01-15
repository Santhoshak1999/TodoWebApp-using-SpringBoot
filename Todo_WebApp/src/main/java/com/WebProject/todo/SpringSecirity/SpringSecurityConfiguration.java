package com.WebProject.todo.SpringSecirity;

import static org.springframework.security.config.Customizer.withDefaults;

import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SpringSecurityConfiguration {
	
//	LDAP or Database
//	In Memory
	
//	public InMemoryUserDetailsManager;
//	public InMemoryUserDetailsManager(UserDetails... users);
	
//	it is a deprecated(outdated) method
	@Bean
	public InMemoryUserDetailsManager createUserDetailsMananger() {
		
		UserDetails userDatails1 = createNewuser("santhosh", "1234");
		UserDetails userDatails2 = createNewuser("Abhishek", "12345");

		return new InMemoryUserDetailsManager(userDatails1, userDatails2);
	}

	private UserDetails createNewuser(String username, String password) {
		Function<String, String> passwordEncoder 
			= input -> passwordEncoder().encode(input);
		UserDetails  userDatails =  User.builder()
				.passwordEncoder(passwordEncoder)
		.username(username)
		.password(password)
		.roles("USER", "ADMIN").build();
		return userDatails;
	}
	
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();	 
	}
	
	
	//All URL are protected
	//A login form is shown for unauthorized requests
	//CSRF disable
	//Frames
	
	//Configuring default feat ures of SpringSecurity
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception{
		http.authorizeHttpRequests(
				auth -> auth.anyRequest().authenticated());
		http.csrf().disable();
		http.headers().frameOptions().disable();
		http.formLogin(withDefaults());
		return http.build();//returning security filterchain back with above modification
	}
	
}
