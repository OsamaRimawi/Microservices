package com.example.phonenotificationservice;

import com.example.phonenotificationservice.event.OrderCreatedEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
@Slf4j
public class PhoneNotificationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhoneNotificationServiceApplication.class, args);
    }

    @KafkaListener(topics = "PhoneNotificationTopic")
    public void sendNotification(OrderCreatedEvent orderCreatedEvent) {
        log.info("phone notification for order - {} - {}", orderCreatedEvent.getOrderNumber(), orderCreatedEvent.getUserInfo());
    }
}
