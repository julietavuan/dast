package com.dast.dast.publisher;

import com.dast.dast.dao.RequestScanning;
import com.dast.dast.model.Scanning;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Component;

@Component
public class KafkaPublisher implements StreamingPublisher {
    private Producer<Long,String> producer = ProducerCreator.createProducer();



    public void publish(RequestScanning scanning){
        ProducerRecord<Long,String> record = new ProducerRecord<>(KafkaConstants.TOPIC_NAME, scanning.getUrl());
        producer.send(record);
    }
}
