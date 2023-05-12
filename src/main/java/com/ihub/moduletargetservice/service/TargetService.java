package com.ihub.moduletargetservice.service;

import com.ihub.moduletargetservice.dto.TargetDTO;
import com.ihub.moduletargetservice.entity.Target;
import com.ihub.moduletargetservice.exception.ResourceAlreadyExistsException;
import com.ihub.moduletargetservice.repository.OrderRepository;
import com.ihub.moduletargetservice.repository.ShiftRepository;
import com.ihub.moduletargetservice.repository.TargetRepository;
import com.ihub.moduletargetservice.util.Mapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class TargetService {
    private final ShiftRepository shiftRepository;
    private final OrderRepository orderRepository;
    private final TargetRepository targetRepository;

    private final Mapper mapper;

    public Target setShiftTarget(TargetDTO targetDTO) {
        Target target = mapper.toEntity(targetDTO);

        if (targetRepository.existsById(target.getId()))
            throw new ResourceAlreadyExistsException("Target already exists.");

        List<Target> shiftsByOrderId = targetRepository.findAllByOrderId(target.getOrder().getId());

        int totalTarget = shiftsByOrderId.stream().mapToInt(Target::getQuantity).sum();

        target.setQuantity(
                Math.max(0, Math.min(target.getOrder().getQuantity() - totalTarget, targetDTO.getTarget()))
        );

        return targetRepository.save(target);
    }

}
