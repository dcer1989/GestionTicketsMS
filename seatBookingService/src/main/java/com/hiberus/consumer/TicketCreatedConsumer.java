package com.hiberus.consumer;

import com.hiberus.avro.dtos.TicketCreatedValue;
import com.hiberus.event.TicketEventCorrelator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TicketCreatedConsumer {

    private final TicketEventCorrelator correlator;

    @KafkaListener(
            topics = "${message.topic.ticket-created}",
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "ticketCreatedKafkaListenerContainerFactory"
    )

    public void receiveTicketCreated(@Header(KafkaHeaders.RECEIVED_KEY) String ticketId,
                                     TicketCreatedValue ticketCreatedValue) {

        log.info("Received event with key=: " + ticketId + " and value: " + ticketCreatedValue);

        correlator.onTicketCreated(ticketId, ticketCreatedValue);
    }

}
