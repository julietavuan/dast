package com.dast.streaming.consumer;
import com.dast.service.SpideringService;
import com.dast.streaming.serlialization.AnalysisResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;
import java.util.List;
@Component
public class KafkaConsumer {
    private final Logger logger = LoggerFactory.getLogger(KafkaConsumer.class);

    @Autowired
    private SpideringService spideringService;

    public void setSpideringService(SpideringService spideringService) {
        this.spideringService = spideringService;
    }

    @KafkaListener(topics = "url_scanned", groupId = "app_consumers")
    public void run(List<AnalysisResult> analysisResultList){
        this.spideringService.saveReport(analysisResultList);
    }
}
