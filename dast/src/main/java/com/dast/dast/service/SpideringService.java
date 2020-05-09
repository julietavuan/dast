package com.dast.dast.service;

import com.dast.dast.dao.RequestScanning;
import com.dast.dast.model.Scanning;
import com.dast.dast.publisher.StreamingPublisher;
import com.dast.dast.repository.ScanningRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SpideringService {

    @Autowired
    private StreamingPublisher streamingPublisher;
    @Autowired
    private ScanningRepository scanningRepository;


    public SpideringService() {

    }

    public StreamingPublisher getStreamingPublisher() {
        return streamingPublisher;
    }

    public void setStreamingPublisher(StreamingPublisher streamingPublisher) {
        this.streamingPublisher = streamingPublisher;
    }

    public void publish(RequestScanning requestScanning){
        Scanning scanning = new Scanning(requestScanning.getUrl());
        scanningRepository.save(scanning);
        this.streamingPublisher.publish(requestScanning);

    }
}
