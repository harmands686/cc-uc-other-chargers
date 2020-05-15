package com.vf.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vf.model.ChildOrder;

@Repository
public interface ChildOrderRepository extends JpaRepository<ChildOrder, Long> {
}