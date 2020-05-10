package com.example.zap.streaming.consumer;

import com.example.zap.service.AttackService;
import com.example.zap.streaming.KafkaConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;


@Component
public class KafkaConsumer implements StreamingConsumer{
    @Autowired
    private AttackService attackService;

    public void setAttackService(AttackService attackService) {
        this.attackService = attackService;
    }

    @KafkaListener(topics = "url_scanning", groupId = "scanners")
    public void run(String url){
        this.attackService.spidering(url);
    }

}
