package com.dast.streaming.publisher;

import com.dast.dao.RequestScanning;


public interface StreamingPublisher {
    void publish(RequestScanning scanning);
}
