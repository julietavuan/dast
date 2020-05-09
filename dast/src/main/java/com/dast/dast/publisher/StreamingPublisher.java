package com.dast.dast.publisher;

import com.dast.dast.dao.RequestScanning;


public interface StreamingPublisher {
    void publish(RequestScanning scanning);
}
