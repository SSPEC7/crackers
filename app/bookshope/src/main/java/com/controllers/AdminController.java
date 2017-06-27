package com.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.Utility;
import com.google.gson.Gson;
import com.models.User;
import com.services.UserService;

@Controller
@ComponentScan("com.services")
@RequestMapping(value = "admin")
public class AdminController {

	final static Logger logger = Logger.getLogger(AdminController.class);
	
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public ModelAndView formLogin() throws IOException {
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("content", "Woowwwww");
		logger.info("Accessed login.");
		return Utility.setView(null, null, "admin/login");
	}
	
	@RequestMapping(value = { "/user-login" }, method = RequestMethod.POST)
	public ModelAndView uaserLogin(@ModelAttribute("userSave") User user, BindingResult result,
			HttpServletRequest request) throws Exception {
		System.out.println("Student Details : " + new Gson().toJson(user));
		try {
			userService.setLoggedIn(user);
		} catch (Exception ex) {
			logger.fatal("error while request user for login.");
		}
		if(userService.isLoggedIn(user)){
			logger.info("Accessed dashboard after user login.");
			return Utility.setView(user.getUserName(), null, "trends/dashboard");
		}
		logger.info("failed user login bad credential.");
		return Utility.setView(null, null, "admin/404");
	}
	
	@RequestMapping(value = { "/{userName}/logout" }, method = RequestMethod.GET)
	public ModelAndView formLogout(@PathVariable("userName")String userName) throws IOException {
		User user = new User();
		try {
			user.setUserName(userName);
			userService.setLoggedOut(user);
		} catch (Exception ex) {
			logger.fatal("error while request user for logout.");
		}
		logger.info("user successfully logout.");
		return Utility.setView(null, null, "admin/login");
	}
	
	@RequestMapping(value = { "/signup" }, method = RequestMethod.GET)
	public ModelAndView formSignUp() throws IOException {
		logger.info("Accessed signup form for user.");
		return Utility.setView(null, null, "admin/signup");
	}
	
	@RequestMapping(value = { "/user-save" }, method = RequestMethod.POST)
	public ModelAndView uaserSave(@ModelAttribute("userSave") User user, BindingResult result,
			HttpServletRequest request) throws Exception {
		System.out.println("Student Details : " + new Gson().toJson(user));
		try {
			userService.save(user);
		} catch (Exception ex) {
			logger.error("error while saving user.");
		}
		logger.info("saved user succeefully.");
		return Utility.setView(null, null, "admin/login");
	}
}
