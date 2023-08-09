package com.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.project.service.TestService;

@Controller
public class TestController {

	@Autowired
	private TestService  testService;
	
	@GetMapping(value= {"/today"})
	public String home(Model model) {
		model.addAttribute("today", testService.selectToday());
		
		return "test";
		
	}
	
	
}
