package com.example.emailnotificationservice;

import com.example.emailnotificationservice.event.OrderCreatedEvent;
import com.example.emailnotificationservice.service.EmailSenderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.kafka.annotation.KafkaListener;

@SpringBootApplication
@Slf4j
public class EmailNotificationServiceApplication {

    @Autowired
    private EmailSenderService emailSenderService;

    public static void main(String[] args) {
        SpringApplication.run(EmailNotificationServiceApplication.class, args);
    }

    @KafkaListener(topics = "EmailNotificationTopic")
    public void sendEmail(OrderCreatedEvent orderCreatedEvent) {
        emailSenderService.sendEmail(orderCreatedEvent.getUserInfo(),orderCreatedEvent.getOrderNumber());
        log.info("email notification for order - {} - {}", orderCreatedEvent.getOrderNumber(), orderCreatedEvent.getUserInfo());
    }
}
