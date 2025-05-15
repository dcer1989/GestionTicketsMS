package com.hiberus.producer;

import com.hiberus.avro.dtos.TicketCreatedValue;
import com.hiberus.avro.dtos.TicketStatus;
import com.hiberus.exception.KafkaSendException;
import com.hiberus.model.Ticket;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TicketCreatedProducer {

    private final KafkaTemplate<String, TicketCreatedValue> kafkaTemplate;

    @Value(value = "${message.topic.output-topic}")
    private String topicName;

    public void sendTicketCreatedMessage(Ticket ticket) {

        String key = String.valueOf(ticket.getId());

        TicketCreatedValue value = TicketCreatedValue.newBuilder()
                .setReservationId(ticket.getReservationId().toString())
                .setFinalPrice(ticket.getPrice())
                .setStatus(TicketStatus.valueOf(ticket.getStatus().name()))
                .build();

        kafkaTemplate.send(topicName, key, value)
                .whenComplete((result, ex) -> {
                    if (ex == null) {

                        log.info("Event sent with key=" + key + " and value=" + value + " to topic=" + topicName);

                    } else {
                        throw new KafkaSendException("Failed to send event with key=" + key + " and value=" + value + "to topic=" + topicName, ex);
                    }
                });
    }
}