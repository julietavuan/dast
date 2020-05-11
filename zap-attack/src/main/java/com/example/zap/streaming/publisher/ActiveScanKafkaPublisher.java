package com.example.zap.streaming.publisher;

import com.example.zap.model.ActiveScan;
import com.example.zap.model.ScanningResponse;
import com.example.zap.streaming.KafkaConstants;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ActiveScanKafkaPublisher implements StreamingPublisher {
    private Producer<Long, ScanningResponse> producer = ActiveScanProducerCreator.createProducer();


    public void publish(ScanningResponse scanningResponse) {
        ProducerRecord<Long, ScanningResponse> record = new ProducerRecord<>(KafkaConstants.TOPIC_NAME_URL_SCANNED, scanningResponse);
        producer.send(record);
    }
}