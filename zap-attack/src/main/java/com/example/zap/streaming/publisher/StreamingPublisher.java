package com.example.zap.streaming.publisher;
import com.example.zap.model.ActiveScan;
import com.example.zap.model.ScanningResponse;

import java.util.List;


public interface StreamingPublisher {
    void publish(ScanningResponse scanning);
}