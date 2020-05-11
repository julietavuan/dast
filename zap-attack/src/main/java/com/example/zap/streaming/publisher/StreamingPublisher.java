package com.example.zap.streaming.publisher;
import com.example.zap.model.ActiveScanResponse;

import java.util.List;


public interface StreamingPublisher {
    void publish(List<ActiveScanResponse> scanning);
}