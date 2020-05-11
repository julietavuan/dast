package com.dast.streaming.consumer;

import com.dast.model.ActiveScan;
import com.dast.model.ScanningResponse;

import java.util.List;

public interface StreamingConsumer {
    void run(ScanningResponse scanningResponse);
}
