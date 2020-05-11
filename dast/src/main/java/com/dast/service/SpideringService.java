package com.dast.service;

import com.dast.dao.RequestScanning;
import com.dast.model.ActiveScan;
import com.dast.model.Scanning;
import com.dast.model.ScanningResponse;
import com.dast.streaming.publisher.StreamingPublisher;
import com.dast.repository.ScanningRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;
import java.util.List;

import java.util.Optional;

@Service
public class SpideringService {
    private final Logger logger = LoggerFactory.getLogger(SpideringService.class);


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

    public Scanning publish(RequestScanning requestScanning){
        Scanning scanning = new Scanning(requestScanning.getUrl());
        Scanning savedScanning = this.scanningRepository.save(scanning);
        this.streamingPublisher.publish(requestScanning);
        return savedScanning;
    }

    public Scanning getScanningByUrl(String url){
        return this.scanningRepository.findByUrl(url);
    }

    public Scanning scanningUpdated(Scanning scanning){
        Date now = new Date();
        if((scanning!=null)&&
                ((scanning.getTime().compareTo(now) > 0) ||
                        scanning.getState().equalsIgnoreCase("Processing"))){
            return null;
        }
        return scanning;
    }

    public Scanning getSpideringResult(String spideringId){
        Optional<Scanning> scanning = this.scanningRepository.findById(spideringId);
        return scanning.orElse(null);

    }

    public void updateScanning(ScanningResponse scanningResponse){
        Scanning scanning = this.scanningRepository.findByUrl(scanningResponse.getUrl());
        scanning.setActiveScanResponses(scanningResponse.getActiveScanResponseList());
        scanning.setState("Done");
        this.scanningRepository.save(scanning);
        this.logger.info("save results");
    }

}
