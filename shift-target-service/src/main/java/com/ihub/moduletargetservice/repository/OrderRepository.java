package com.ihub.moduletargetservice.repository;

import com.ihub.moduletargetservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String> {
}
