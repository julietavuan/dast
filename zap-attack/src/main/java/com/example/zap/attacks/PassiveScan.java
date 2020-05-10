package com.example.zap.attacks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.zaproxy.clientapi.core.ApiResponseElement;
import org.zaproxy.clientapi.core.ClientApi;

import static com.example.zap.attacks.ZapConstants.*;

public class PassiveScan {
    private final Logger logger = LoggerFactory.getLogger(PassiveScan.class);

    public void scanning(String url) {
        ClientApi api = new ClientApi(ZAP_ADDRESS, ZAP_PORT, ZAP_API_KEY);
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