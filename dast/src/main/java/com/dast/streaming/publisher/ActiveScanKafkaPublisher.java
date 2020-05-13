package com.dast.streaming.publisher;

import com.dast.service.SpideringService;
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

    @Autowired
    private KafkaTemplate<Long, String> kafkaTemplate;
    private final Logger logger = LoggerFactory.getLogger(ActiveScanKafkaPublisher.class);
    @Autowired
    private SpideringService spideringService;
    @Value("${kafka.active.scan.topic.name}")
    private String topicName;


    public void publish(String url){
        this.logger.info("Send URL to Scan");
        ListenableFuture<SendResult<Long, String>> future = kafkaTemplate.send(topicName, url);

        future.addCallback(new ListenableFutureCallback<SendResult<Long, String>>() {
            @Override
            public void onSuccess(SendResult<Long, String> result) {
                System.out.println("Sent message=[" + url + "]");
            }

            @Override
            public void onFailure(Throwable ex) {
                System.err.println("Unable to send message=[" + url + "] due to : " + ex.getMessage());
                spideringService.failPublishScan(url);
            }
        });
    }
}
