package com.dast.streaming.consumer;

import com.dast.model.ScanningResponse;
import com.dast.streaming.serlialization.ActiveScanResultDeserializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;

@EnableKafka
@Configuration
public class ActiveScanConsumerCreator {

    @Value("${kafka.broker}")
    private String kafkaBroker;

    @Value("${kafka.group.id}")
    private String groupId;

    @Bean
    public DefaultKafkaConsumerFactory<Long,ScanningResponse> consumerFactory(){
        Map<String,Object> properties = new HashMap<>();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaBroker);
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, groupId);
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ActiveScanResultDeserializer.class.getName());
        return new DefaultKafkaConsumerFactory<Long, ScanningResponse>(properties);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<Long, ScanningResponse>
    kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<Long, ScanningResponse> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}
