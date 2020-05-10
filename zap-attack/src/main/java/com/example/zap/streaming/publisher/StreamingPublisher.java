package com.example.zap.streaming.publisher;

import com.example.zap.model.ResponseScanning;

import java.util.stream.Stream;


public interface StreamingPublisher {
    void publish(Stream<ResponseScanning> scanning);
}