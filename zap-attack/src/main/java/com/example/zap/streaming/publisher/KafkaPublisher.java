package com.example.zap.streaming.publisher;

import com.example.zap.model.ActiveScanResponse;
import com.example.zap.streaming.KafkaConstants;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class KafkaPublisher implements StreamingPublisher {
    private Producer<Long, List<ActiveScanResponse>> producer = ProducerCreator.createProducer();


    public void publish(List<ActiveScanResponse> analysisResultList) {
        ProducerRecord<Long, List<ActiveScanResponse>> record = new ProducerRecord<>(KafkaConstants.TOPIC_NAME_URL_SCANNED, analysisResultList);
        producer.send(record);
    }
}