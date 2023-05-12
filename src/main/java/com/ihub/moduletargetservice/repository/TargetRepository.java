package com.ihub.moduletargetservice.repository;

import com.ihub.moduletargetservice.entity.Target;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TargetRepository extends JpaRepository<Target, Long> {
    List<Target> findAllByOrderId(String orderId);
}
