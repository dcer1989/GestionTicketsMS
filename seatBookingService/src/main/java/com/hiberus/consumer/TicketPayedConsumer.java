package com.hiberus.consumer;

import com.hiberus.avro.dtos.TicketPayedValue;
import com.hiberus.event.TicketEventCorrelator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TicketPayedConsumer {

    private final TicketEventCorrelator correlator;

    @KafkaListener(
            topics = "${message.topic.ticket-payed}",
            groupId = "${spring.kafka.consumer.group-id}",
            containerFactory = "ticketPayedKafkaListenerContainerFactory"
        )
    public void receiveTicketPayed(TicketPayedValue ticketPayedValue) {

        log.info("Received TicketPayed event for ticketId with value: " + ticketPayedValue);

        correlator.onTicketPayed(ticketPayedValue);
    }

}
