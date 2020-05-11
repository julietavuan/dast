package com.dast.streaming.consumer;
import com.dast.model.ActiveScanResponse;
import com.dast.service.SpideringService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.List;

@Component
public class

ActiveScanKafkaConsumer implements StreamingConsumer{
    @Autowired
    private SpideringService spideringService;

    public void setSpideringService(SpideringService spideringService) {
        this.spideringService = spideringService;
    }

    @KafkaListener(topics = "url_scanned", groupId = "app_consumers")
    public void run(List<ActiveScanResponse> activeScanResponses){
        this.spideringService.saveReport(activeScanResponses);
    }

   /* public void run(byte[] activeScanResponses){
        ObjectMapper objectMapper = new ObjectMapper();
        List<ActiveScanResponse> pepe = null;
        try {
            pepe = objectMapper.readValue(activeScanResponses, new TypeReference<List<ActiveScanResponse>>(){});
        } catch (IOException e) {
            e.printStackTrace();
        }
        this.spideringService.saveReport(pepe);
    }*/
}
