import com.hiberus.ApplicationTicketPurchaseService;
import com.hiberus.avro.dtos.TicketCreatedValue;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.kafka.core.KafkaTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(classes = {ApplicationTicketPurchaseService.class, KafkaTestConfig.class})
public class KafkaTemplateConfigurationTest {

    @Autowired
    private KafkaTemplate<String, TicketCreatedValue> kafkaTemplate;

    @Test
    void testKafkaTemplateConfiguration() {
        // Verificar que el KafkaTemplate no sea nulo
        assertThat(kafkaTemplate).isNotNull();

        // Verificar que los serializadores estén configurados correctamente
        var producerFactory = kafkaTemplate.getProducerFactory();
        var configProps = producerFactory.getConfigurationProperties();

        assertThat(configProps.get(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG))
                .isEqualTo("io.confluent.kafka.serializer.StringSerializer");
        assertThat(configProps.get(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG))
                .isEqualTo("io.confluent.kafka.serializer.KafkaAvroSerializer");
        assertThat(configProps.get("schema.registry.url"))
                .isEqualTo("http://localhost:8081");
    }
}