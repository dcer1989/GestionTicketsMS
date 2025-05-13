package com.hiberus.config;

import com.hiberus.avro.dtos.TicketCreatedValue;
import com.hiberus.avro.dtos.TicketPayedValue;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import io.confluent.kafka.serializers.KafkaAvroDeserializerConfig;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class KafkaConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    @Value("${spring.kafka.properties.schema.registry.url}")
    private String schemaRegistryUrl;

    // Configuración para TicketCreatedValue
    @Bean
    public ConsumerFactory<String, TicketCreatedValue> ticketCreatedConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class);
        props.put(KafkaAvroDeserializerConfig.SCHEMA_REGISTRY_URL_CONFIG, schemaRegistryUrl);
        props.put(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, true);
        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean(name = "ticketCreatedKafkaListenerContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<String, TicketCreatedValue> ticketCreatedKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, TicketCreatedValue> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(ticketCreatedConsumerFactory());
        return factory;
    }

    // Configuración para TicketPayedValue
    @Bean
    public ConsumerFactory<String, TicketPayedValue> ticketPayedConsumerFactory() {
        Map<String, Object> props = new HashMap<>();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class);
        props.put(KafkaAvroDeserializerConfig.SCHEMA_REGISTRY_URL_CONFIG, schemaRegistryUrl);
        props.put(KafkaAvroDeserializerConfig.SPECIFIC_AVRO_READER_CONFIG, true);
        return new DefaultKafkaConsumerFactory<>(props);
    }

    @Bean(name = "ticketPayedKafkaListenerContainerFactory")
    public ConcurrentKafkaListenerContainerFactory<String, TicketPayedValue> ticketPayedKafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, TicketPayedValue> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(ticketPayedConsumerFactory());
        return factory;
    }
}