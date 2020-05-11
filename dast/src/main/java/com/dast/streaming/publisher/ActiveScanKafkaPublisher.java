package com.dast.streaming.publisher;

import com.dast.dao.RequestScanning;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.springframework.stereotype.Component;

@Component
public class ActiveScanKafkaPublisher implements StreamingPublisher {
    private Producer<Long,String> producer = ActiveScanProducerCreator.createProducer();

    public void publish(String url){
        ProducerRecord<Long,String> record = new ProducerRecord<>(KafkaConstants.TOPIC_NAME_SCANNING_TOPIC, url);
        producer.send(record);
    }
}
