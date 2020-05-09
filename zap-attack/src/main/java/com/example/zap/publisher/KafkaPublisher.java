package com.example.zap.publisher;

import com.example.zap.model.ResponseScanning;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Component;

@Component
public class KafkaPublisher implements StreamingPublisher {
    private Producer<Long, String> producer = ProducerCreator.createProducer();


    public void publish(ResponseScanning scanning) {
        //TO DO resposneScanning
        ProducerRecord<Long, String> record = new ProducerRecord<>(KafkaConstants.TOPIC_NAME, "url");
        producer.send(record);
    }
}