package com.ihub.shifttargetservice.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ihub.shifttargetservice.event.ShiftEvent;
import com.ihub.shifttargetservice.service.ShiftService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ShiftConsumer {

    private final ShiftService shiftService;
    private final ObjectMapper objectMapper;

    @KafkaListener(topics = "${spring.kafka.topic.shift}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(@Payload String payload, @Header("event-type") String eventType) {
        log.info("Order event received in shift-target service => %s", payload);

        try {
            ShiftEvent shiftEvent = objectMapper.readValue(payload, ShiftEvent.class);

            switch (eventType) {
                case "create" -> processCreateEvent(shiftEvent);
                case "delete" -> processDeleteEvent(shiftEvent);
                default -> log.warn("Unknown event type: " + eventType);
            }

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private void processCreateEvent(ShiftEvent shiftEvent) {
        shiftService.createShift(shiftEvent);
    }

    private void processDeleteEvent(ShiftEvent shiftEvent) {
        shiftService.deleteShift(shiftEvent);
    }
}
