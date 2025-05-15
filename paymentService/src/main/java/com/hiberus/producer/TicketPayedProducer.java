package com.hiberus.producer;

import com.hiberus.avro.dtos.PaymentStatus;
import com.hiberus.avro.dtos.TicketPayedValue;
import com.hiberus.exception.KafkaSendException; // Importar la nueva excepción personalizada
import com.hiberus.model.Payment;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class TicketPayedProducer {

    private final KafkaTemplate<String, TicketPayedValue> kafkaTemplate;

    @Value("${message.topic.ticket-payed}")
    private String topicName;

    public void sendTicketPayedMessage(Payment payment) {

        String key = String.valueOf(payment.getPaymentId());

        TicketPayedValue value = TicketPayedValue.newBuilder()
                .setTicketId(String.valueOf(payment.getTicketId()))
                .setFinalPrice(payment.getFinalPrice())
                .setPaymentMethod(payment.getPaymentMethod())
                .setPaymentTimestamp(payment.getPaymentTimestamp())
                .setStatus(PaymentStatus.valueOf(payment.getStatus().name()))
                .build();

        kafkaTemplate.send(topicName, key, value)
                .whenComplete((result, ex) -> {
                    if (ex == null) {

                        log.info("Event sent with key=" + key + " and value=" + value + " to topic=" + topicName);

                    } else {

                        throw new KafkaSendException("Failed to send event with key=" + key + " and value=" + value + " to topic=" + topicName, ex);
                    }
                });
    }
}