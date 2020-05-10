package com.example.zap.attacks;

import org.zaproxy.clientapi.core.ApiResponseElement;
import org.zaproxy.clientapi.core.ClientApi;

import java.nio.charset.StandardCharsets;
import java.util.logging.Logger;

import static com.example.zap.attacks.ZapConstants.*;

public class PassiveScan {
    private Logger logger;

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
            this.logger.severe("Exception : " + e.getMessage());
        }
    }
}