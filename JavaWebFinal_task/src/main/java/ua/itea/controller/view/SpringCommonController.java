package ua.itea.controller.view;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "view")
public class SpringCommonController {
	
	@GetMapping(path = "index")
	public String index()  {
		return "index";
	}
}
