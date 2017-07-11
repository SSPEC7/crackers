package com.controllers;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

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
import com.aerospike.models.Person;
import com.aerospike.services.PersonService;
import com.google.gson.Gson;
import com.models.User;
import com.redis.Student;
import com.redis.StudentRepository;
import com.services.UserService;

@Controller
@ComponentScan("com.services,com.aerospike.services, com.redis")
@RequestMapping(value = "admin")
public class AdminController {

	final static Logger logger = Logger.getLogger(AdminController.class);
	
	@Autowired
	@Qualifier("userService")
	private UserService userService;
	
	@Autowired
	@Qualifier("studentRepository")
	private StudentRepository studentRepository;
	
	@Autowired
	@Qualifier("personService")
	private PersonService personService;
	
	@RequestMapping(value = { "/login" }, method = RequestMethod.GET)
	public ModelAndView formLogin() throws IOException {
		
		Person p = new Person();
		
		p.setAge(40);
		p.setId("HAJK374GHPQ");
		p.setEmail("ritesh@gmail.com");
		p.setFirstname("RITESH");
		p.setLastName("SINGH");
		p.setCreatedAt(new Date());
		
		personService.save(p);
		
		Person pe = personService.findById("HAJK374GHPQ"); 
		
		System.out.println("hello"+pe.getId()+" "+pe.getAge());
		
		List<Person> ps1 = personService.findByFirstName("RITESH");
		
		List<Person> ps = personService.findByAgeBetween(10, 50);
		
		List<Person> psl = personService.findByLastName("SINGH");
		
		/*Student student = new Student();
		student.setId("sdfhsdk");
		student.setGrade(345);
		student.setName("hello");
		
		studentRepository.save(student);*/
		
		//Student student = studentRepository.findStudent("sdfhsdk");
		
		
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
