package com.ihub.shifttargetservice.data;

import com.ihub.shifttargetservice.entity.Order;
import com.ihub.shifttargetservice.entity.Shift;
import com.ihub.shifttargetservice.enums.ShiftType;
import com.ihub.shifttargetservice.repository.OrderRepository;
import com.ihub.shifttargetservice.repository.ShiftRepository;
import com.ihub.shifttargetservice.repository.TargetRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
@AllArgsConstructor
public class SampleDataLoader implements CommandLineRunner {

    private final ShiftRepository shiftRepository;

    @Override
    public void run(String... args) throws Exception {

        shiftRepository.saveAll(
                List.of(
                        Shift.builder().lineId("LN0001").shiftType(ShiftType.A).date(LocalDate.now()).build(),
                        Shift.builder().lineId("LN0001").shiftType(ShiftType.B).date(LocalDate.now()).build(),
                        Shift.builder().lineId("LN0002").shiftType(ShiftType.A).date(LocalDate.now()).build()
                )
        );

    }

}
