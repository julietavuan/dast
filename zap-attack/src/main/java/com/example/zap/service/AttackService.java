package com.example.zap.service;

import com.example.zap.attacks.Scanner;
import com.example.zap.attacks.Spidering;
import com.example.zap.model.ActiveScanResponse;
import com.example.zap.streaming.publisher.StreamingPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zaproxy.clientapi.core.ApiResponse;

import java.util.ArrayList;
import java.util.List;

@Service
public class AttackService {
    @Autowired
    private Spidering spidering;
    @Autowired
    private Scanner scanner;
    @Autowired
    private StreamingPublisher streamingPublisher;

    public void spidering(String url){
        List<ApiResponse> urlSpidering = this.spidering.spidering(url);
/*        List<AnalysisResult> analysisResults = urlSpidering.stream()
                .map(element -> this.scanner.scan(element))
                .collect(Collectors.toList());*/
        ActiveScanResponse activeScanResponse = this.scanner.scan(urlSpidering.get(0));
        List<ActiveScanResponse> analysisResults = new ArrayList<>();
        analysisResults.add(activeScanResponse);
        this.streamingPublisher.publish(analysisResults);

    }

}
