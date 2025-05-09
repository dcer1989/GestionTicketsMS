package com.hiberus.listener;

import com.hiberus.avro.dtos.TicketCreatedValue;
import com.hiberus.consumer.TicketConsumer;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TicketCreatedListener {

    private final TicketConsumer ticketConsumer;

    @KafkaListener(topics = "${message.topic.name}", groupId = "${spring.kafka.consumer.group-id}")
    public void onMessage(TicketCreatedValue event) {
        ticketConsumer.consume(event);
    }
}
