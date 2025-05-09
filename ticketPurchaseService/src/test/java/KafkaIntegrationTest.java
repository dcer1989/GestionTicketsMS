import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.jupiter.api.Test;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.context.EmbeddedKafka;
import org.springframework.kafka.test.utils.KafkaTestUtils;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@EmbeddedKafka(partitions = 1, topics = {"test-topic"})
public class KafkaIntegrationTest {

    @Test
    void testKafkaProducerAndConsumer(EmbeddedKafkaBroker embeddedKafka) {
        // Configuración del productor
        Map<String, Object> producerProps = KafkaTestUtils.producerProps(embeddedKafka);
        producerProps.put("key.serializer", StringSerializer.class);
        producerProps.put("value.serializer", StringSerializer.class);
        DefaultKafkaProducerFactory<String, String> producerFactory = new DefaultKafkaProducerFactory<>(producerProps);
        var producer = producerFactory.createProducer();

        // Enviar un mensaje
        String topic = "test-topic";
        String key = "test-key";
        String value = "test-value";
        producer.send(new ProducerRecord<>(topic, key, value));
        producer.flush();

        // Configuración del consumidor
        Map<String, Object> consumerProps = KafkaTestUtils.consumerProps("test-group", "true", embeddedKafka);
        consumerProps.put("key.deserializer", StringDeserializer.class);
        consumerProps.put("value.deserializer", StringDeserializer.class);
        DefaultKafkaConsumerFactory<String, String> consumerFactory = new DefaultKafkaConsumerFactory<>(consumerProps);
        var consumer = consumerFactory.createConsumer();
        embeddedKafka.consumeFromAnEmbeddedTopic(consumer, topic);

        // Verificar el mensaje recibido
        ConsumerRecord<String, String> record = KafkaTestUtils.getSingleRecord(consumer, topic);
        assertThat(record.key()).isEqualTo(key);
        assertThat(record.value()).isEqualTo(value);

        // Cerrar recursos
        consumer.close();
        producer.close();
    }
}