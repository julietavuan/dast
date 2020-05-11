package com.example.zap.streaming.publisher;

import com.example.zap.model.ActiveScanResponse;
import com.example.zap.streaming.KafkaConstants;

import java.util.List;

import com.example.zap.streaming.serialization.ActiveScanResultSerializer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.LongSerializer;

import java.util.Properties;

public class ProducerCreator {

    public static Producer<Long,List<ActiveScanResponse>> createProducer(){
        Properties properties = new Properties();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConstants.KAFKA_BROKER);
        properties.put(ProducerConfig.CLIENT_ID_CONFIG, KafkaConstants.CLIENT_ID);
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, LongSerializer.class.getName());
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, ActiveScanResultSerializer.class);
        return new KafkaProducer<Long, List<ActiveScanResponse>>(properties);
    }
}
