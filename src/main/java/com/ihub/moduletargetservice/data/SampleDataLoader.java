package com.ihub.moduletargetservice.data;

import com.ihub.moduletargetservice.entity.Order;
import com.ihub.moduletargetservice.entity.Shift;
import com.ihub.moduletargetservice.entity.ShiftTarget;
import com.ihub.moduletargetservice.enums.ShiftType;
import com.ihub.moduletargetservice.repository.OrderRepository;
import com.ihub.moduletargetservice.repository.ShiftRepository;
import com.ihub.moduletargetservice.repository.ShiftTargetRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
@AllArgsConstructor
public class SampleDataLoader implements CommandLineRunner {

    private final OrderRepository orderRepository;
    private final ShiftRepository shiftRepository;
    private final ShiftTargetRepository shiftTargetRepository;

    @Override
    public void run(String... args) throws Exception {


        Shift shift = Shift.builder().lineId("LN0001").shiftType(ShiftType.A).date(LocalDate.now()).build();

        Order order = Order.builder().id("OD00001").quantity(100_000).build();

        ShiftTarget target = ShiftTarget.builder().target(100).order(order).shift(shift).build();

        shift.setTarget(target);

        orderRepository.save(order);
        shiftRepository.save(shift);


    }

}
