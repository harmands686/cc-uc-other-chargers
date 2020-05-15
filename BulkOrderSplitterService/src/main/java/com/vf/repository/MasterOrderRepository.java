package com.vf.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vf.model.MasterOrder;

@Repository
public interface MasterOrderRepository extends JpaRepository<MasterOrder, Long> {
}