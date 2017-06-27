package com.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.Utility;

@Controller
@ComponentScan("com.services")
@RequestMapping(value = "book")
public class BookController {
	
	final static Logger logger = Logger.getLogger(BookController.class);
	
	@RequestMapping(value = { "/" }, method = RequestMethod.GET)
	public ModelAndView formLogin() throws IOException {
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("content", "Woowwwww");
		logger.info("Accessed book-list.");
		return Utility.setView("userName", data, "book/save");
	}
}
