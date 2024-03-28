package com.notification.rabbitmq;

import com.amigoscode.notification.NotificationRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.notification.NotificationService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@AllArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final NotificationService notificationService;
    private final ObjectMapper objectMapper; // Injectez l'objet ObjectMapper

    @RabbitListener(queues = "${rabbitmq.queues.notification}")
    public void consumer(byte[] messageBytes) {
        try {
            NotificationRequest notificationRequest = objectMapper.readValue(messageBytes, NotificationRequest.class);
            log.info("Consommé {} depuis la file d'attente", notificationRequest);
            notificationService.send(notificationRequest);
        } catch (IOException e) {
            log.error("Erreur lors de la désérialisation du message JSON", e);
        }
    }

/*    @RabbitListener(queues = "${rabbitmq.queues.notification}")
    public void consumer(NotificationRequest notificationRequest) {
        log.info("Consumed {} from queue", notificationRequest);
        notificationService.send(notificationRequest);
    }*/
}
