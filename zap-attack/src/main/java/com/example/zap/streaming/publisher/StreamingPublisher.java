package com.example.zap.streaming.publisher;

import com.example.zap.model.ResponseScanning;


public interface StreamingPublisher {
    void publish(ResponseScanning scanning);
}