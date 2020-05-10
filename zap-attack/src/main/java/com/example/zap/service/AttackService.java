package com.example.zap.service;

import com.example.zap.attacks.Scanner;
import com.example.zap.attacks.Spidering;
import com.example.zap.model.ResponseScanning;
import com.example.zap.streaming.publisher.StreamingPublisher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.zaproxy.clientapi.core.ApiResponse;

import java.util.List;
import java.util.stream.Stream;

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
        Stream<ResponseScanning> stringStream = urlSpidering.stream().map(element ->
                this.scanner.scan(element));
        this.streamingPublisher.publish(stringStream);

    }
}
