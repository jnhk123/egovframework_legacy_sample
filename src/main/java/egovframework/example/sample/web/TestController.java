package egovframework.example.sample.web;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import egovframework.example.sample.service.TestService;

@Controller
public class TestController {
	
	private final TestService testService;

	public TestController(TestService testService) {
		this.testService = testService;
	}
	
	@ResponseBody
	@RequestMapping(value = "/data.do", method = RequestMethod.GET)
	public ResponseEntity<?> data() {
		return ResponseEntity.ok(testService.selectMethod());
	}
	
	@ResponseBody
	@RequestMapping(value = "/data/data", method = RequestMethod.GET)
	public ResponseEntity<?> getData() {
		return ResponseEntity.ok(testService.selectMethod());
	}
	
	
}
