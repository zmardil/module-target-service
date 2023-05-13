package com.ihub.shifttargetservice.service;

import com.ihub.shifttargetservice.dto.TargetDTO;
import com.ihub.shifttargetservice.entity.Target;
import com.ihub.shifttargetservice.exception.ResourceAlreadyExistsException;
import com.ihub.shifttargetservice.repository.OrderRepository;
import com.ihub.shifttargetservice.repository.ShiftRepository;
import com.ihub.shifttargetservice.repository.TargetRepository;
import com.ihub.shifttargetservice.util.Mapper;
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
            throw new ResourceAlreadyExistsException("Target already assigned to shift.");

        List<Target> shiftsByOrderId = targetRepository.findAllByOrderId(target.getOrder().getId());

        int totalTarget = shiftsByOrderId.stream().mapToInt(Target::getQuantity).sum();

        int allocatableQuantity = target.getOrder().getQuantity() - totalTarget;

        if(target.getQuantity() > 0 && target.getQuantity() > allocatableQuantity) throw new IllegalArgumentException("Target quantity exceeds allocatable quantity");

        return targetRepository.save(target);
    }

}
