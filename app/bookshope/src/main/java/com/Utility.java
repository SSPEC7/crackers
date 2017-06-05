package com;

import java.util.HashMap;
import java.util.Map;

import org.springframework.web.servlet.ModelAndView;

public class Utility {

	public static ModelAndView setView(String userName, Map<String, Object> data,String viewPage){
		if(data == null)
			data = new HashMap<String, Object>();
		data.put("userName", userName);
		NumberFormatter numberFormatter = new NumberFormatter();
		data.put("numberFormatter", numberFormatter);
		return new ModelAndView(viewPage, "data", data);
	}
}