package com.samryder.employee_service.Repository;

import com.samryder.employee_service.Entity.EmployeeEntity;
import com.samryder.employee_service.Model.Employee;


import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long>, CrudRepository<EmployeeEntity, Long>{
	
	//@Query("SELECT c FROM EmployeeEntity c WHERE c.name LIKE '%'||:keyword || '%'"
        //    + " OR c.email LIKE '%' || :keyword || '%'")
            //+ " OR c.address LIKE '%' || :keyword || '%'") 
	
	//@Query (value = "SELECT e FROM EmployeeEntity e WHERE e.name LIKE %:keyword% OR e.email LIKE %:keyword%")
	//@Query (value = "SELECT e FROM EmployeeEntity e WHERE e.name LIKE CONCAT('%', keyword, '%') OR e.email LIKE CONCAT('%', keyword, '%')")
	//@Query(value = "SELECT * FROM employeedb WHERE name LIKE CONCAT('%', :keyword , '%') OR email LIKE CONCAT('%', :keyword, '%')", nativeQuery = true)
	@Query("SELECT e FROM EmployeeEntity e WHERE lower(e.name) LIKE lower(concat('%', :keyword, '%')) OR lower(e.email) LIKE lower(concat('%', :keyword, '%'))")
	@Transactional (readOnly = true)
	List<EmployeeEntity> search (@Param("keyword") String keyword);
	
}