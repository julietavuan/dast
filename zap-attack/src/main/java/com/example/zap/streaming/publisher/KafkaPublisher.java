package com.example.zap.streaming.publisher;

import com.example.zap.model.ResponseScanning;
import com.example.zap.streaming.KafkaConstants;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Component;

@Component
public class KafkaPublisher implements StreamingPublisher {
    private Producer<Long, String> producer = ProducerCreator.createProducer();


    public void publish(ResponseScanning scanning) {
        //TO DO resposneScanning
        ProducerRecord<Long, String> record = new ProducerRecord<>(KafkaConstants.TOPIC_NAME_URL_SCANNED, "url");
        producer.send(record);
    }
}