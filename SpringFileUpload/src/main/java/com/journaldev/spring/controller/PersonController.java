package com.journaldev.spring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class PersonController {

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String home() {
		return "/person/student";
	}
}
