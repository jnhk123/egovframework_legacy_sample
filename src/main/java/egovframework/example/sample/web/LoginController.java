package egovframework.example.sample.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class LoginController {
	
	@RequestMapping(value = "/login.do", method= RequestMethod.GET)
	public String select(Model model) {
		return "sample/login";
	}
	
}
