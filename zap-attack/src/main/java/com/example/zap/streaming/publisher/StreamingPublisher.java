package com.example.zap.streaming.publisher;
import com.example.zap.streaming.serialization.AnalysisResult;

import java.util.List;


public interface StreamingPublisher {
    void publish(List<AnalysisResult> scanning);
}