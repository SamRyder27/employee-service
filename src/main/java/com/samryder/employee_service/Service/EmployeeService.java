package com.samryder.employee_service.Service;

import java.util.List;
import org.springframework.context.annotation.Bean;
import com.samryder.employee_service.Model.Employee;

public interface EmployeeService{

	String createEmployee (Employee employee);
	List<Employee> readEmployees ();
	boolean deleteEmployee (Long id);
	String updateEmployee (Long id, Employee employee);
	List<Employee> searchByName (String keyword);
	
}