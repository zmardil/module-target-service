package com.ihub.shifttargetservice.repository;

import com.ihub.shifttargetservice.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String> {
}
