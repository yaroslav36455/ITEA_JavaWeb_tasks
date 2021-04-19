package ua.itea.controller.view;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import ua.itea.controller.api.handler.user.signin.SignInBlockerHandler;
import ua.itea.controller.api.util.SessionAttributeManager;
import ua.itea.model.users.User;

@Controller
@RequestMapping(path = "view/user")
public class UserController {
	@Autowired
	private SessionAttributeManager sam;
	@Autowired
	private SignInBlockerHandler sibh;

	@GetMapping(path = "sign-up")
	public String signUp() {
		return "sign-up";
	}
	
	@GetMapping(path = "sign-in")
	public String signIn() {	
		if(sam.hasAttribute(User.class)) {
			return "redirect:home";
		} else {
			sibh.update();
			return "sign-in";
		}
	}
	
	@GetMapping(path = "home")
	public String home() {		
		if(sam.hasAttribute(User.class)) {
			return "user-home";
		} else {
			return "redirect:sign-in";
		}
	}
}
