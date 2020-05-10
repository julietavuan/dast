package com.example.zap.service;

import com.example.zap.attacks.Scanner;
import com.example.zap.attacks.Spidering;
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

    public void spidering(String url){
        List<ApiResponse> urlSpidering = this.spidering.spidering(url);
        Stream<String> stringStream = urlSpidering.stream().map(element ->
                this.scanner.scan(element));
    }
}
