package com.ihub.shifttargetservice.kafka;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ihub.shifttargetservice.entity.Order;
import com.ihub.shifttargetservice.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderConsumer {

    private final OrderService orderService;

    private final ObjectMapper mapper;

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(@Payload String payload, @Header("event-type") String eventType) {
        log.info(String.format("Order event received in stock service => %s", payload));

        try {
            JsonNode node = mapper.readTree(payload);
            String id = node.get("id").asText();
            int quantity = node.get("quantity").asInt();
            Order order = Order.builder().id(id).quantity(quantity).build();
            if (eventType.equals("create")) {
                orderService.saveOrder(order);
            } else if (eventType.equals("update")) {
                // update order logic
            } else if (eventType.equals("cancel")) {
                // cancel order logic
            } else {
                log.warn("Unknown event type: " + eventType);
            }
        } catch (Exception e) {
            log.error("Error processing order event: " + payload, e);
        }
    }
}

