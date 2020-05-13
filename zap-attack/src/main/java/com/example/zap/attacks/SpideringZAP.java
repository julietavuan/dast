package com.example.zap.attacks;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.zaproxy.clientapi.core.ApiResponse;
import org.zaproxy.clientapi.core.ApiResponseElement;
import org.zaproxy.clientapi.core.ApiResponseList;
import org.zaproxy.clientapi.core.ClientApi;
import java.util.List;

@Component
public class SpideringZAP implements Spidering{
    private final Logger logger = LoggerFactory.getLogger(SpideringZAP.class);
    @Value("${zap.address}")
    private String address;
    @Value("${zap.port}")
    private String port;
    @Value("${zap.api.key}")
    private String apiKey;
    public List<ApiResponse> spidering(String url){
        ClientApi api = new ClientApi(address, Integer.parseInt(port), apiKey);
        List<ApiResponse> spiderResults = null;
        try {
            System.out.println("Spidering target : " + url);
            ApiResponse resp = api.spider.scan(url, "3", null, null, null);
            String scanID;
            int progress;
            scanID = ((ApiResponseElement) resp).getValue();
            while (true) {
                Thread.sleep(1000);
                progress = Integer.parseInt(((ApiResponseElement) api.spider.status(scanID)).getValue());
                this.logger.info("Spider progress : " + progress + "%");
                if (progress >= 100) {
                    break;
                }
            }
            this.logger.info("Spider completed");
            spiderResults = ((ApiResponseList) api.spider.results(scanID)).getItems();

        } catch (Exception e) {
            this.logger.error("Exception : " + e.getMessage());
        }
        return spiderResults;
    }
}