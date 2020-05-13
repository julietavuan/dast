package com.dast.streaming.consumer;
import com.dast.model.ScanningResponse;
import com.dast.service.SpideringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class

ActiveScanKafkaConsumer implements StreamingConsumer{
    @Autowired
    private SpideringService spideringService;

    public void setSpideringService(SpideringService spideringService) {
        this.spideringService = spideringService;
    }

    @KafkaListener(topics = "url_scanned", groupId = "app_consumers")
    public void run(ScanningResponse scanningResponse){
        this.spideringService.updateScanning(scanningResponse);
    }

}
