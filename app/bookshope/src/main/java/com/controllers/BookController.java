package com.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.Utility;
import com.google.gson.Gson;
import com.models.Book;
import com.models.User;

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
	
	@RequestMapping(value = { "/save" }, method = RequestMethod.POST)
	public ModelAndView uaserLogin(@ModelAttribute("bookSave") Book book, BindingResult result,
			HttpServletRequest request) throws Exception {
		System.out.println("Student Details : " + new Gson().toJson(book));
		
		logger.info("failed user login bad credential.");
		return Utility.setView(null, null, "admin/404");
	}
}
