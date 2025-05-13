package com.hiberus.service;

import com.hiberus.model.Payment;
import com.hiberus.model.PaymentStatus;
import com.hiberus.repository.PaymentsRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.Random;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class CreatePaymentService {

    private final PaymentsRepository paymentsRepository;
    private final Random random = new Random();

    public Payment createPayment(UUID ticketId, double amount) {

        log.info("Creating payment for ticket ID: {}", ticketId);

        PaymentStatus paymentStatus = random.nextBoolean() ? PaymentStatus.PAYED : PaymentStatus.FAILED;

        Payment payment = new Payment();
        payment.setPaymentId(UUID.randomUUID());
        payment.setTicketId(ticketId);
        payment.setFinalPrice(amount);
        payment.setPaymentMethod("CREDIT_CARD");
        payment.setPaymentTimestamp(Instant.now());
        payment.setStatus(paymentStatus);

        log.info("Saving payment with ID: {}", payment.getPaymentId());

        return paymentsRepository.save(payment);
    }
}
