import com.hiberus.avro.dtos.TicketCreatedValue;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.HashMap;
import java.util.Map;

@TestConfiguration
public class KafkaTestConfig {

    public KafkaTemplate<String, TicketCreatedValue> kafkaTemplate() {
        Map<String, Object> configProps = new HashMap<>();
        configProps.put("bootstrap.servers", "localhost:9092");
        configProps.put("key.serializer", "io.confluent.kafka.serializer.StringSerializer");
        configProps.put("value.serializer", "io.confluent.kafka.serializer.KafkaAvroSerializer");
        configProps.put("schema.registry.url", "http://localhost:8081");

        return new KafkaTemplate<>(new DefaultKafkaProducerFactory<>(configProps));
    }
}