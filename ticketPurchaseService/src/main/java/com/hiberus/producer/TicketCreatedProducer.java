package com.hiberus.producer;

import com.hiberus.avro.dtos.TicketCreatedValue;
import com.hiberus.model.Ticket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class TicketCreatedProducer {

    @Autowired
    private KafkaTemplate<String, TicketCreatedValue> kafkaTemplate;


    @Value(value = "${message.topic.name}")
    private String topicName;

    public void sendTicketCreatedMessage(Ticket ticket) {
        // Construir la clave del mensaje
        String key = String.valueOf(ticket.getId());

        // Construir el valor del mensaje
        TicketCreatedValue value = TicketCreatedValue.newBuilder()
                .setReservationId(ticket.getReservationId().toString())
                .setFinalPrice(ticket.getPrice())
                .setStatus(com.hiberus.avro.dtos.TicketStatus.valueOf(ticket.getStatus().name()))
                .build();

        // Enviar el mensaje al tópico
        CompletableFuture<SendResult<String, TicketCreatedValue>> future = kafkaTemplate.send(topicName, key, value);
        future.whenComplete((result, ex) -> {
            if (ex == null) {
                System.out.println("Sent message=[" + value.toString() +
                        "] with offset=[" + result.getRecordMetadata().offset() + "]");
            } else {
                System.out.println("Unable to send message=[" +
                        value.toString() + "] due to : " + ex.getMessage());
            }
        });
    }
}