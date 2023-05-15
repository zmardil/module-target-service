package com.ihub.shiftservice.repository;

import com.ihub.shiftservice.entity.Shift;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShiftRepository extends JpaRepository<Shift, String> {
}
