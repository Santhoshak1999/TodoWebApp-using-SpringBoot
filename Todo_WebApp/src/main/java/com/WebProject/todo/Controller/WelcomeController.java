package com.WebProject.todo.Controller;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
public class WelcomeController {
	
	
//	@Autowired
//	private AuthenticationService authenticationService;
/*
	@RequestParam
	@RequestParam binds a URL query parameter to a method parameter in Spring.
	use get value from URL to Controller
	
	ModelMap model
	To pass multiple attributes from a Spring controller to the view for rendering.
	
*/
	//To handle both GET and POST		onlySupportsGet
	@RequestMapping(value="/", method=RequestMethod.GET)
//	@ResponseBody
	public String gotoWelcomePage(ModelMap modelmap) {
		modelmap.put("name", getLoggedinUsername());
		return "welcome";
	}
	
//	Get the current Username from authentication details
	private String getLoggedinUsername() {
//		Access the Current User's Details:
//		authentication object, which contains details about the currently authenticated user
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication(); 
//		get user name from the authentication details
		return authentication.getName();
	}
	
	
	
	
}
