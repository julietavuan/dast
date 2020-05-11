package com.dast.streaming.consumer;

import com.dast.model.ActiveScanResponse;

import java.util.List;

public interface StreamingConsumer {
    void run(List<ActiveScanResponse> activeScanResponses);
}
