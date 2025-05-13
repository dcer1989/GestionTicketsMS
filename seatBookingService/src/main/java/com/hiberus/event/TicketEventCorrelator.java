package com.hiberus.event;

import com.hiberus.avro.dtos.PaymentStatus;
import com.hiberus.avro.dtos.TicketCreatedValue;
import com.hiberus.avro.dtos.TicketPayedValue;
import com.hiberus.service.UpdateReservationToCompletedService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

@Service
@RequiredArgsConstructor
@Slf4j
public class TicketEventCorrelator {

    private final UpdateReservationToCompletedService updateReservationToCompletedService;

    private final Map<String, TicketCreatedValue> createdMap = new ConcurrentHashMap<>();
    private final Map<String, TicketPayedValue> payedMap = new ConcurrentHashMap<>();

    /**
     * Llamado por el listener cuando se recibe un TicketCreated.
     * @param ticketId key del mensaje Kafka
     * @param created payload del evento
     */
    public void onTicketCreated(String ticketId, TicketCreatedValue created) {

        createdMap.put(ticketId, created);

        log.debug("Added ticket with ID: {} to createdMap", ticketId);

        TicketPayedValue payed = payedMap.get(ticketId);
        if (payed != null) {

            log.info("Found matching TicketPayed event for ticket with ID: {}", ticketId);

            processReservation(ticketId, created, payed);
        } else {

            log.debug("No matching TicketPayed event found for ticket with ID: {}", ticketId);
        }
    }

    public void onTicketPayed(TicketPayedValue payed) {
        String ticketId = payed.getTicketId();

        payedMap.put(ticketId, payed);

        log.debug("Added ticket with ID: {} to payedMap", ticketId);

        TicketCreatedValue created = createdMap.get(ticketId);
        if (created != null) {

            log.info("Found matching TicketCreated event for ticket with ID: {}", ticketId);

            processReservation(ticketId, created, payed);
        } else {

            log.debug("No matching TicketCreated event found for ticket with ID: {}", ticketId);

        }
    }

    private void processReservation(String ticketId, TicketCreatedValue created, TicketPayedValue payed) {
        String reservationId = created.getReservationId();

        log.info("Processing reservation for ticket with ID: {} (reservation ID: {})", ticketId, reservationId);

        if (payed.getStatus() == PaymentStatus.PAYED) {

            log.info("Payment status is PAYED. Updating reservation with ID {} to COMPLETED.", reservationId);

            updateReservationToCompletedService.updateReservationCompleted(UUID.fromString(reservationId));
        } else {
            log.warn("Payment status is FAILED. Skipping update for reservation with ID {} as expired.", reservationId);
        }

        createdMap.remove(ticketId);
        payedMap.remove(ticketId);

        log.debug("Removed ticket ID: {} from createdMap and payedMap", ticketId);
    }
}
