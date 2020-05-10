package com.example.zap.attacks;

import com.example.zap.model.ResponseScanning;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.zaproxy.clientapi.core.ApiResponse;
import org.zaproxy.clientapi.core.ApiResponseElement;
import org.zaproxy.clientapi.core.ClientApi;
import java.nio.charset.StandardCharsets;

import static com.example.zap.attacks.ZapConstants.*;

@Component
public class ActiveScan implements Scanner {
    private final Logger logger = LoggerFactory.getLogger(ActiveScan.class);

    public ResponseScanning scan(ApiResponse urlRequest) {

        ClientApi api = new ClientApi(ZAP_ADDRESS, ZAP_PORT, ZAP_API_KEY);
        ResponseScanning responseScanning = new ResponseScanning();
        try {
            String url = ((ApiResponseElement)urlRequest).getValue();
            System.out.println("Active Scanning target : " + url);
            ApiResponse resp = api.ascan.scan(url, "True", "False", null, null, null);
            String scanid;
            int progress;

            // The scan now returns a scan id to support concurrent scanning
            scanid = ((ApiResponseElement) resp).getValue();
            while (true) {
                Thread.sleep(5000);
                progress =
                        Integer.parseInt(
                                ((ApiResponseElement) api.ascan.status(scanid)).getValue());
                this.logger.info("Active Scan progress : " + progress + "%");
                if (progress >= 100) {
                    break;
                }
            }
            this.logger.info("Active Scan complete");
            responseScanning.setUrl(url);
            responseScanning.setAlerts(new String(api.core.jsonreport(), StandardCharsets.UTF_8));


        } catch (Exception e) {
            this.logger.error("Exception : " + e.getMessage());
        }
        return responseScanning;
    }
}