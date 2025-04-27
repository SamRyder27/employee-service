package com.samryder.employee_service.Repository;

import com.samryder.employee_service.Entity.EmployeeEntity;
import com.samryder.employee_service.Model.Employee;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long>{
	
	@Query(value = "SELECT c FROM Employee c WHERE c.name LIKE '%'||:keyword || '%'"
            + " OR c.email LIKE '%' || :keyword || '%'")
            //+ " OR c.address LIKE '%' || :keyword || '%'") 
	List<Employee> search (@Param("keyword") String keyword);
	
}