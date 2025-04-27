package com.samryder.employee_service.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.samryder.employee_service.Model.Employee;
import com.samryder.employee_service.Service.EmployeeService;
import com.samryder.employee_service.Service.EmployeeServiceImp;
//import com.samryder.employee_service.Service.EmployeeServiceImp;


@RestController
public class EmployeeController {
	
	//EmployeeService employeeService = new EmployeeServiceImp();
	
	//Dependency Injection 
	@Autowired
	private EmployeeService employeeService;
	public EmployeeController (EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	//List<Employee> employees = new ArrayList<>();
	@GetMapping("employees")//its working now
	public  List<Employee> getAllemployess () {
			
		return employeeService.readEmployees(); 
	}
	
	@PostMapping("employees")
	public String createEmployee (@RequestBody Employee employee) {
		
		return employeeService.createEmployee(employee);
	
	} 
	
	@DeleteMapping("employees/{id}")
	public String deleteEmployee (@PathVariable Long id) {
		if (employeeService.deleteEmployee(id))
			return "Deleted Successfully";
		return "ID Not found";
		
	}
	
	@PutMapping("employees/{id}")
	public String updateEmployee (@PathVariable Long id, @RequestBody Employee employee) {
		
		return employeeService.updateEmployee(id, employee);
	}

}