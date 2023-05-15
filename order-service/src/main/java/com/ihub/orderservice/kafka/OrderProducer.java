package com.ihub.orderservice.kafka;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ihub.orderservice.event.OrderEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class OrderProducer {

    private final NewTopic topic;

    private final ObjectMapper mapper;

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendMessage(OrderEvent orderEvent, String eventType){
        log.info(String.format("Order event -> %s", orderEvent.toString()));

        String payload;
        try {
            payload = mapper.writeValueAsString(orderEvent);
        } catch (JsonProcessingException e) {
            log.error("Error serializing Order to JSON", e);
            return;
        }

        Message<String> message = MessageBuilder
                .withPayload(payload)
                .setHeader(KafkaHeaders.TOPIC, topic.name())
                .setHeader("event-type", eventType)
                .build();

        kafkaTemplate.send(message);
    }
}
