package com.company.repository;

import java.util.List;

import javax.transaction.Transactional;

import com.company.entity.Employee;
import com.company.entity.Order;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order,Long>{
	
	@Transactional
	@Modifying
	@Query(value = "UPDATE orders SET Status =:status WHERE order_id = :orderId",nativeQuery = true)
	void updateStatusById(String status, long orderId);
	
	@Transactional
	@Modifying
	List<Order>findByStatus(String status);
	
	
	@Transactional
	@Modifying
	List<Order>findByEmployee(Employee emp);
	
}
