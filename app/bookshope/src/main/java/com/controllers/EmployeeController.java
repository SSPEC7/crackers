package com.controllers;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.lang.reflect.Type;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.google.gson.Gson;
import com.models.EmployeeRequest;
import com.services.EmployeeService;

@Controller
@ComponentScan("com.services")
@RequestMapping(value = "admin/employee")
public class EmployeeController {

	@Autowired
	@Qualifier("employeeService")
	private EmployeeService employeeService;
	
	
	@RequestMapping(value = { "/create" }, method = RequestMethod.GET)
	public ModelAndView form() throws IOException {
		EmployeeRequest employee = new EmployeeRequest();
		employee.setName("RITESH SINGH");
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("content", "Woowwwww");
		return new ModelAndView("admin/employee", "data", data);
	}

	@RequestMapping(value = { "/save" }, method = RequestMethod.POST)
	public ModelAndView save(@ModelAttribute("employeeSave") EmployeeRequest employee, BindingResult result,
			HttpServletRequest request) throws Exception {
		System.out.println("Student Details : " + new Gson().toJson(employee));
		try {
			employeeService.save(employee);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		Map<String, Object> data = new HashMap<String, Object>();
		return new ModelAndView("user/employee", "data", data);
	}
	
	@RequestMapping(value = { "/get-employees" }, method = RequestMethod.GET)
	public ModelAndView getEmployeeRequests() throws IOException {
		EmployeeRequest employee = new EmployeeRequest();
		employee.setName("RITESH SINGH");
		
		List<EmployeeRequest> employees = new ArrayList<EmployeeRequest>();
		employees.add(employee);
		
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("employees", employeeService.getEmployees());
		
		System.out.println("Final Data= "+new Gson().toJson(data));
		
		return new ModelAndView("employee/employee", "data", data);
	}
	
}