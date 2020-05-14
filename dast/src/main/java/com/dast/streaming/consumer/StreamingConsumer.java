package com.dast.streaming.consumer;

import com.dast.streaming.model.ScanningResponse;

public interface StreamingConsumer {
    void run(ScanningResponse scanningResponse);
}
