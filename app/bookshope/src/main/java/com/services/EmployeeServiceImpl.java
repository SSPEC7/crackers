package com.services;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.Response;
import com.dao.DataAccessObject;
import com.google.gson.Gson;
import com.models.EmployeeRequest;

@Service("employeeService")
public class EmployeeServiceImpl extends DataAccessObject implements EmployeeService {

	@Value("${employee.save.api}")
	private String saveApi;
	
	@Value("${employee.get.api}")
	private String getApi;
	
	@Value("${ip}")
	private String ip;
	
	@Value("${port}")
	private String port;
	
	public void save(Object employee) throws IOException{
 
		String url = ip+port;
		String data = new Gson().toJson(employee);
		
		Map<String, String> header = new HashMap<String, String>();
		
		header.put("token", "myToken");
		
		sendPOST(url+saveApi, data, header);
	}
	
	public EmployeeRequest fetchEmployeeBySsn(Object employee) throws IOException{
		 
		String url = ip+port;
		String data = new Gson().toJson(employee);
		try{
			
			Map<String, String> header = new HashMap<String, String>();
			
			header.put("token", "myToken");
			
			EmployeeRequest fetchedEmployee = new Gson().fromJson(new Gson().toJson(sendPOST(url+getApi, data, header)), EmployeeRequest.class);
			 
			return fetchedEmployee;
		}catch(Exception ee){
			
		}
		return null;
	}

	@Override
	public List<EmployeeRequest> getEmployees() {
		Gson gson = new Gson();
		String url = ip+port;
		try{
			
			Map<String, String> header = new HashMap<String, String>();
			
			header.put("token", "myToken");
			
			Response apiResponse = gson.fromJson(sendGET(url+getApi, header), Response.class);
			
			System.out.println("apiResponse = "+new Gson().toJson(apiResponse));
			
			if(apiResponse.getStatus().equals("200")){
				List<EmployeeRequest> employees = gson.fromJson(gson.toJson(apiResponse.getData()), List.class);
				return employees;
			}
		}catch(Exception ee){
			
		}
		
		return null;
	}
}