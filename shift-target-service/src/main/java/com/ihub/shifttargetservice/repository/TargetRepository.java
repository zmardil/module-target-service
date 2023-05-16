package com.ihub.shifttargetservice.repository;

import com.ihub.shifttargetservice.entity.Target;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TargetRepository extends JpaRepository<Target, String> {
    List<Target> findAllByOrderId(String orderId);
}
