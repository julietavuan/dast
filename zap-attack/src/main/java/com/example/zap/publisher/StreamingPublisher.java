package com.example.zap.publisher;

import com.example.zap.model.ResponseScanning;


public interface StreamingPublisher {
    void publish(ResponseScanning scanning);
}