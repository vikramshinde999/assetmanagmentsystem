package com.company.repository;

import java.util.List;

import javax.transaction.Transactional;

import com.company.entity.Employee;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Repository;


@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Long>{
	
	@Transactional
	@Modifying
	List<Employee>findByEmailId(String email);
}
