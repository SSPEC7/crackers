package com.services;

import java.io.IOException;
import java.util.List;

import com.models.EmployeeRequest;

public interface EmployeeService {

	public void save(Object employee) throws IOException;
	
	public Object fetchEmployeeBySsn(Object employee) throws IOException;
	
	public List<EmployeeRequest> getEmployees();
}