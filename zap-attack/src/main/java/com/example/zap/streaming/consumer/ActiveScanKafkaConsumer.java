package com.example.zap.streaming.consumer;

import com.example.zap.service.AttackService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
public class ActiveScanKafkaConsumer implements StreamingConsumer{
    private final Logger logger = LoggerFactory.getLogger(ActiveScanKafkaConsumer.class);

    @Autowired
    private AttackService attackService;

    public void setAttackService(AttackService attackService) {
        this.attackService = attackService;
    }

    @KafkaListener(topics = "url_scanning", groupId = "scanners")
    public void run(String url){
        this.logger.info("Get URL to scan");
        this.attackService.spidering(url);
    }

}
