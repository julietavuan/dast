package com.example.zap.attacks;

import org.zaproxy.clientapi.core.ApiResponseElement;
import org.zaproxy.clientapi.core.ClientApi;

import java.nio.charset.StandardCharsets;

public class PassiveScan implements Scanning {

    private static final int ZAP_PORT = 8080;
    private static final String ZAP_API_KEY = null;
    private static final String ZAP_ADDRESS = "localhost";

    public void scanning(String url) {
        ClientApi api = new ClientApi(ZAP_ADDRESS, ZAP_PORT, ZAP_API_KEY);
        int numberOfRecords;

        try {
            // TODO : explore the app (Spider, etc) before using the Passive Scan API, Refer the explore section for details
            // Loop until the passive scan has finished
            while (true) {
                Thread.sleep(2000);
                api.pscan.recordsToScan();
                numberOfRecords = Integer.parseInt(((ApiResponseElement) api.pscan.recordsToScan()).getValue());
                System.out.println("Number of records left for scanning : " + numberOfRecords);
                if (numberOfRecords == 0) {
                    break;
                }
            }
            System.out.println("Passive Scan completed");

            // Print Passive scan results/alerts
            System.out.println("Alerts:");
            System.out.println(new String(api.core.xmlreport(), StandardCharsets.UTF_8));

        } catch (Exception e) {
            System.out.println("Exception : " + e.getMessage());
            e.printStackTrace();
        }
    }
}