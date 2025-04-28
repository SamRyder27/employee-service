package com.samryder.employee_service.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.samryder.employee_service.Model.Employee;
import com.samryder.employee_service.Repository.EmployeeRepository;
import com.samryder.employee_service.Entity.EmployeeEntity;
//CRUD operations and logic is implemented here
@Service
public class EmployeeServiceImp implements EmployeeService {
	
	//EmployeeRepository employeeRepository =new EmployeeRepository ();
	//parameterised constructor for dependancy injection
	@Autowired
	private EmployeeRepository employeeRepository;
	public EmployeeServiceImp (EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}


	@Override
	public String createEmployee(Employee employee) {
		// TODO Auto-generated method stub
		EmployeeEntity employeeEntity = new EmployeeEntity ();
		BeanUtils.copyProperties(employee, employeeEntity);
		employeeRepository.save(employeeEntity);
		//employees.add(employee);
		return "Saved Successfully";
	}

	@Override
	public List<Employee> readEmployees() {
		// TODO Auto-generated method stub
		List<EmployeeEntity> employeeList = employeeRepository.findAll();
		List<Employee> employees = new ArrayList<>();
		
		for(EmployeeEntity employeeEntity : employeeList) {
			Employee emp = new Employee();
			emp.setId(employeeEntity.getId());
			emp.setName(employeeEntity.getName());
			emp.setEmail(employeeEntity.getEmail());
			emp.setPhone(employeeEntity.getPhone());
			employees.add(emp);
			
		}
		
		return employees;
	}

	@Override
	public boolean deleteEmployee (Long id) {
		EmployeeEntity emp = employeeRepository.findById(id).get();
		employeeRepository.delete(emp);
		//employees.remove(id);
		return true;
	}


	@Override
	public String updateEmployee(Long id, Employee employee) {
		EmployeeEntity presentEmp = employeeRepository.findById(id).get();
		presentEmp.setEmail(employee.getEmail());
		presentEmp.setName(employee.getName());
		presentEmp.setPhone(employee.getPhone());
		employeeRepository.save(presentEmp);
		
		return "Updated Successfully";
	}


	@Override
	public List<Employee> searchByName(String keyword) {
		
		List<Employee> emplist = Collections.emptyList(); 
		//try {
			 emplist = employeeRepository.search(keyword);	
		//}
//		catch (EmptyResultDataAccessException e) {
//			System.out.println("No employees found matching the keyword.");
//			}
		return emplist;
	}

}
