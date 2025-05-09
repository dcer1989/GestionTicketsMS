package com.hiberus.consumer;

import com.hiberus.avro.dtos.TicketCreatedValue;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class TicketConsumer {

    public void consume(TicketCreatedValue ticketCreatedValue) {
        log.info("Ticket created event consumed: {}", ticketCreatedValue);
        // Process the ticketCreatedValue as needed
    }
}
