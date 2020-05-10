package com.dast.service;

import com.dast.controller.SpideringController;
import com.dast.dao.RequestScanning;
import com.dast.model.Scanning;
import com.dast.streaming.publisher.StreamingPublisher;
import com.dast.repository.ScanningRepository;
import com.dast.streaming.serlialization.AnalysisResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.ZoneOffset;
import java.time.ZonedDateTime;
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
        ZonedDateTime now = ZonedDateTime.now(ZoneOffset.UTC);
        Scanning scanning = new Scanning(requestScanning.getUrl(), now);
        scanning.setId(1L);
        Scanning scanning1 = this.scanningRepository.save(scanning);
        this.streamingPublisher.publish(requestScanning);
        return scanning1;
    }

    public Scanning getScanning(RequestScanning newScanning){
        return this.scanningRepository.findByUrl(newScanning.getUrl());
    }

    public Scanning getSpideringResult(Long spideringId){
        Optional<Scanning> scanning = this.scanningRepository.findById(spideringId.toString());
        return scanning.get();
    }

    public void saveReport(List<AnalysisResult >analysisResultList){
        this.logger.info("save results");
    }

}
