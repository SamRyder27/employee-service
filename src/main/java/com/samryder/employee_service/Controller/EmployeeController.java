package com.samryder.employee_service.Controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.samryder.employee_service.Entity.EmployeeEntity;
import com.samryder.employee_service.Exceptions.ResourceNotFoundException;
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
			List <Employee> allemp = employeeService.readEmployees();
		return allemp;
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
	
	@GetMapping ("employees/search")
	public ResponseEntity<List<Employee>> searchByName (@RequestParam String keyword) throws ResourceNotFoundException
	{
		System.out.println("Searching for keyword: " + keyword);  // log
		List <Employee> result = employeeService.searchByName(keyword);
		
		System.out.println("Found employees: " + result.size());  // log
       //ModelAndView mav = new ModelAndView("search");
        //mav.addObject("result", result);
		return ResponseEntity.ok(result);
	}

}