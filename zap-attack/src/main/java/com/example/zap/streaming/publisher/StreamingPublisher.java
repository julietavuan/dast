package com.example.zap.streaming.publisher;
import com.example.zap.model.ScanningResponse;

public interface StreamingPublisher {
    void publish(ScanningResponse scanning);
}