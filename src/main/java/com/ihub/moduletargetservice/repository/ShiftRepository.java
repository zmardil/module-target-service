package com.ihub.moduletargetservice.repository;

import com.ihub.moduletargetservice.entity.Shift;
import com.ihub.moduletargetservice.enums.ShiftType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ShiftRepository extends JpaRepository<Shift, Long> {
    Optional<Shift> findByLineIdAndShiftTypeAndDate(
            @Param("line_id") String lineId,
            @Param("shift_type") ShiftType shiftType,
            @Param("date") LocalDate date
    );

    List<Shift> findAllByShiftType(@Param("shift_type") ShiftType shiftType);
}
