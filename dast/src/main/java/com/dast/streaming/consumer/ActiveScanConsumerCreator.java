package com.dast.streaming.consumer;

import com.dast.model.ActiveScanResponse;
import com.dast.streaming.publisher.KafkaConstants;
import com.dast.streaming.serlialization.ActiveScanResultDeserializer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.LongDeserializer;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

@EnableKafka
@Configuration
public class ActiveScanConsumerCreator {
    @Bean
    public static DefaultKafkaConsumerFactory<Long,List<ActiveScanResponse>> consumerFactory(){
        Map<String,Object> properties = new HashMap<>();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConstants.KAFKA_BROKER);
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, KafkaConstants.GROUP_ID);
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, LongDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ActiveScanResultDeserializer.class.getName());
        return new DefaultKafkaConsumerFactory<Long, List<ActiveScanResponse>>(properties);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<Long, List<ActiveScanResponse>>
    kafkaListenerContainerFactory() {

        ConcurrentKafkaListenerContainerFactory<Long, List<ActiveScanResponse>> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}
