package com.example.zap.streaming.publisher;

import com.example.zap.model.ResponseScanning;
import com.example.zap.streaming.KafkaConstants;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Component;

import java.util.stream.Stream;

@Component
public class KafkaPublisher implements StreamingPublisher {
    private Producer<Long, String> producer = ProducerCreator.createProducer();


    public void publish(Stream<ResponseScanning> scanning) {
        ProducerRecord<Long, String> record = new ProducerRecord<>(KafkaConstants.TOPIC_NAME_URL_SCANNED, "url");
        producer.send(record);
    }
}