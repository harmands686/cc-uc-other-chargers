package com.vf.repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.vf.model.ChildOrder;

@Repository
public interface ChildOrderRepository extends JpaRepository<ChildOrder, Long> {

	@Query("SELECT a FROM ChildOrder a WHERE a.orderStatus = ?1")
	Collection<ChildOrder> findChildOrderByOrderStatus(String orderStatus);

}