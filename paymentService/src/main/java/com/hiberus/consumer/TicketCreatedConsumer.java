package com.hiberus.consumer;

import com.hiberus.avro.dtos.TicketCreatedValue;
import com.hiberus.avro.dtos.PaymentStatus;
import com.hiberus.avro.dtos.TicketPayedValue;
import com.hiberus.model.Payment;
import com.hiberus.producer.TicketPayedProducer;
import com.hiberus.service.CreatePaymentService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Random;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class TicketCreatedConsumer {

    private final TicketPayedProducer ticketPayedProducer;
    private final CreatePaymentService createPaymentService;
    private final Random random = new Random();

    @KafkaListener(
            topics = "${message.topic.ticket-created}",
            groupId = "${spring.kafka.consumer.group-id}"
    )
    public void consumeTicketCreated(
            @Header(KafkaHeaders.RECEIVED_KEY) String ticketId,
            TicketCreatedValue ticketCreatedValue
    ) {
        log.info("Received event with ID (ticketId): " + ticketId + " and value(ticketCreatedValue): " + ticketCreatedValue);

        log.info("Creating payment for ticket with ID: " + ticketId);

        Payment payment = createPaymentService.createPayment(UUID.fromString(ticketId), ticketCreatedValue.getFinalPrice());

        log.info("Sending payment event for ticket with ID: " + ticketId);

        ticketPayedProducer.sendTicketPayedMessage(payment);
    }
}
