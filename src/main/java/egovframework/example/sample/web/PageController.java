package egovframework.example.sample.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping(PageController.PAGE_CONTOLLER_PREFIX)
public class PageController {
	
	public static final String PAGE_CONTOLLER_PREFIX = "/page";
	
	@RequestMapping(value = "/chart", method= RequestMethod.GET)
	public String goChart(Model model) {
		return "sample/chart";
	}
	
	@RequestMapping(value = "/input", method= RequestMethod.GET)
	public String goInput(Model model) {
		return "sample/input";
	}
	

}
