package com.example.zap.attacks;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.zaproxy.clientapi.core.ApiResponse;
import org.zaproxy.clientapi.core.ApiResponseElement;
import org.zaproxy.clientapi.core.ClientApi;

@Component
public class ActiveScanZAP implements Scanner {
    private final Logger logger = LoggerFactory.getLogger(ActiveScanZAP.class);
    @Value("${zap.address}")
    private String address;
    @Value("${zap.port}")
    private String port;
    @Value("${zap.api.key}")
    private String apiKey;

    public com.example.zap.model.ActiveScan scan(ApiResponse urlRequest) {

        ClientApi api = new ClientApi(address, Integer.parseInt(port), apiKey);
        com.example.zap.model.ActiveScan activeScanResponse = new com.example.zap.model.ActiveScan();
        try {
            String url = ((ApiResponseElement)urlRequest).getValue();
            System.out.println("Active Scanning target : " + url);
            ApiResponse resp = api.ascan.scan(url, "False", "True", null, null, null);
            String scanid;
            int progress;
            scanid = ((ApiResponseElement) resp).getValue();
            boolean finish = false;
            while (!finish) {
                Thread.sleep(5000);
                progress =
                        Integer.parseInt(
                                ((ApiResponseElement) api.ascan.status(scanid)).getValue());
                this.logger.info("Active Scan progress : " + progress + "%");
                if (progress >= 100) {
                    finish = true;
                }
            }
            this.logger.info("Active Scan complete");
            ObjectMapper objectMapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            activeScanResponse = objectMapper.readValue(api.core.jsonreport(), com.example.zap.model.ActiveScan.class);

        } catch (Exception e) {
            this.logger.error("Exception : " + e.getMessage());
        }

        return activeScanResponse;
    }
}