package com.aytuncbakir.lms.controller;


import java.util.Date;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class TestController {
	
	@RequestMapping(value="/test")
	public String testPage(Model model) {
		model.addAttribute("hello","Merhaba agalar");
		return "test";
	}
	
	@RequestMapping(value="/test/ajax_test")
	public String testPageAjax() {
		
		return "test";
	}
	
	@RequestMapping(value="/test/get_time")
	@ResponseBody
	public String getServerTime() {
		System.out.println("-----------getServerTime-----------");
		Date date = new Date();
		return date.toString();
	}

}
