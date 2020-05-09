package com.example.zap.attacks;

import org.zaproxy.clientapi.core.ApiResponse;
import org.zaproxy.clientapi.core.ApiResponseElement;
import org.zaproxy.clientapi.core.ApiResponseList;
import org.zaproxy.clientapi.core.ClientApi;
import java.util.List;

public class Spider {

    private static final String ZAP_ADDRESS = "localhost";
    private static final int ZAP_PORT = 8080;
    // Change to match the API key set in ZAP, or use NULL if the API key is disabled
    private static final String ZAP_API_KEY = "change me";

    public List<ApiResponse> spidering(String url){
        ClientApi api = new ClientApi(ZAP_ADDRESS, ZAP_PORT, ZAP_API_KEY);
        List<ApiResponse> spiderResults = null;
        try {
            // Start spidering the target
            System.out.println("Spidering target : " + url);
            ApiResponse resp = api.spider.scan(url, null, null, null, null);
            String scanID;
            int progress;

            // The scan returns a scan id to support concurrent scanning
            scanID = ((ApiResponseElement) resp).getValue();
            // Poll the status until it completes
            while (true) {
                Thread.sleep(1000);
                progress = Integer.parseInt(((ApiResponseElement) api.spider.status(scanID)).getValue());
                System.out.println("Spider progress : " + progress + "%");
                if (progress >= 100) {
                    break;
                }
            }
            System.out.println("Spider completed");
            // If required post process the spider results
            spiderResults = ((ApiResponseList) api.spider.results(scanID)).getItems();
            // TODO: Explore the Application more with Ajax Spider or Start scanning the application for vulnerabilities

        } catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
            e.printStackTrace();
        }
        return spiderResults;
    }
}