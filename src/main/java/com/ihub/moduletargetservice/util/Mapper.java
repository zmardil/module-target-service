package com.ihub.moduletargetservice.util;

import com.ihub.moduletargetservice.dto.TargetDTO;
import com.ihub.moduletargetservice.entity.Order;
import com.ihub.moduletargetservice.entity.Shift;
import com.ihub.moduletargetservice.entity.Target;
import com.ihub.moduletargetservice.exception.ResourceNotFoundException;
import com.ihub.moduletargetservice.repository.OrderRepository;
import com.ihub.moduletargetservice.repository.ShiftRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class Mapper {

    private final ShiftRepository shiftRepository;

    private final OrderRepository orderRepository;

    public TargetDTO toDTO(Target target) {
        return TargetDTO.builder()
                .quantity(target.getQuantity())
                .lineId(target.getShift().getLineId())
                .shiftType(target.getShift().getShiftType())
                .orderId(target.getOrder().getId())
                .date(target.getShift().getDate())
                .build();
    }

    public Target toEntity(TargetDTO targetDTO) {
        Shift shift = shiftRepository.findByLineIdAndShiftTypeAndDate(
                targetDTO.getLineId(),
                targetDTO.getShiftType(),
                targetDTO.getDate()
        ).orElseThrow(
                () -> new ResourceNotFoundException("Shift of line id " + targetDTO.getLineId() + ", ShiftType " + targetDTO.getShiftType() + " & date " + targetDTO.getDate() + " not found.")
        );

        Order order = orderRepository.findById(targetDTO.getOrderId()).orElseThrow(
                () -> new ResourceNotFoundException("Order of id " + targetDTO.getOrderId() + " not found.")
        );

        return Target.builder()
                .id(shift.getId())
                .order(order)
                .shift(shift)
                .quantity(targetDTO.getQuantity())
                .build();
    }

}
