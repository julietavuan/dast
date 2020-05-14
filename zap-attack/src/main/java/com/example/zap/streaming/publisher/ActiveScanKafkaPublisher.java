package com.example.zap.streaming.publisher;

import com.example.zap.model.ScanningResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;
import org.springframework.util.concurrent.ListenableFuture;
import org.springframework.util.concurrent.ListenableFutureCallback;

@Component
public class ActiveScanKafkaPublisher implements StreamingPublisher {
    private final Logger logger = LoggerFactory.getLogger(ActiveScanKafkaPublisher.class);

    @Autowired
    private KafkaTemplate<Long, ScanningResponse> kafkaTemplate;

    @Value("${kafka.url.scanned.topic.name}")
    private String topicName;

    public void publish(ScanningResponse scanningResponse) {
        ListenableFuture<SendResult<Long, ScanningResponse>> future = kafkaTemplate.send(topicName, scanningResponse);

        future.addCallback(new ListenableFutureCallback<SendResult<Long, ScanningResponse>>() {
            @Override
            public void onSuccess(SendResult<Long, ScanningResponse> result) {
                logger.info("Sent vulnerabilities of url=[" + result.getProducerRecord().value().getUrl() + "]");
            }

            @Override
            public void onFailure(Throwable ex) {
                logger.error("Unable to send message due to : " + ex.getMessage());
            }
        });
    }
}