package com.ihub.shifttargetservice.kafka;


import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ihub.shifttargetservice.entity.Order;
import com.ihub.shifttargetservice.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class OrderConsumer {

    @Autowired
    private OrderService orderService;

    private static final Logger LOGGER= LoggerFactory.getLogger(OrderConsumer.class);

    @KafkaListener(topics = "${spring.kafka.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void consume(@Payload String payload, @Header("event-type") String eventType) {
        LOGGER.info(String.format("Order event received in stock service => %s", payload));

        ObjectMapper mapper = new ObjectMapper();
        Order order;
        try {
            JsonNode node = mapper.readTree(payload);
            String orderId = node.get("orderId").asText();
            int quantity = node.get("qty").asInt();
            order = Order.builder().id(orderId).quantity(quantity).build();
            if (eventType.equals("create")) {
                orderService.saveOrder(order);
            } else if (eventType.equals("update")) {
                // update order logic
            } else if (eventType.equals("cancel")) {
                // cancel order logic
            } else {
                LOGGER.warn("Unknown event type: " + eventType);
            }
        } catch (Exception e) {
            LOGGER.error("Error processing order event: " + payload, e);
        }
    }
}

