package com.example.zap.attacks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.zaproxy.clientapi.core.ApiResponseElement;
import org.zaproxy.clientapi.core.ClientApi;

public class PassiveScan {
    private final Logger logger = LoggerFactory.getLogger(PassiveScan.class);
    @Value("${zap.address}")
    private String address;
    @Value("${zap.port}")
    private String port;
    @Value("${zap.api.key}")
    private String apiKey;
    public void scanning(String url) {
        ClientApi api = new ClientApi(address, Integer.parseInt(port), apiKey);
        int numberOfRecords;

        try {
            while (true) {
                Thread.sleep(2000);
                api.pscan.recordsToScan();
                numberOfRecords = Integer.parseInt(((ApiResponseElement) api.pscan.recordsToScan()).getValue());
                this.logger.info("Number of records left for scanning : " + numberOfRecords);
                if (numberOfRecords == 0) {
                    break;
                }
            }
            this.logger.info("Passive Scan completed");

        } catch (Exception e) {
            this.logger.error("Exception : " + e.getMessage());
        }
    }
}