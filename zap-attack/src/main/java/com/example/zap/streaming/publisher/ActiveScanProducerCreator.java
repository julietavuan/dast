package com.example.zap.streaming.publisher;

import com.example.zap.model.ScanningResponse;
import com.example.zap.streaming.serialization.ActiveScanResultSerializer;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.LongSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
public class ActiveScanProducerCreator {

    @Value("${kafka.broker}")
    private String kafkaBroker;

    @Value("${kafka.client.id}")
    private String clientId;

    @Bean
    public ProducerFactory<Long, ScanningResponse> producerFactory(){
        Map<String, Object> properties = new HashMap<>();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBroker);
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, clientId);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, ActiveScanResultSerializer.class.getName());
        return new DefaultKafkaProducerFactory<>(properties);
    }

    @Bean
    public KafkaTemplate<Long, ScanningResponse> kafkaTemplate() {
        return new KafkaTemplate<Long, ScanningResponse>(producerFactory());
    }
}
