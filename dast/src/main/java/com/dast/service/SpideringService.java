package com.dast.service;

import com.dast.dao.RequestScanning;
import com.dast.model.ActiveScanResponse;
import com.dast.model.Scanning;
import com.dast.model.Site;
import com.dast.streaming.publisher.StreamingPublisher;
import com.dast.repository.ScanningRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
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
        Scanning scanning1 = this.scanningRepository.save(scanning);
        this.streamingPublisher.publish(requestScanning);
        return scanning1;
    }

    public Scanning getScanningByUrl(RequestScanning newScanning){
        Scanning scanning = this.scanningRepository.findByUrl(newScanning.getUrl());
        Date now = new Date();
        if((scanning!=null)&&(scanning.getTime().compareTo(now) > 6)){
            return null;
        }
        return scanning;
    }

    public Scanning getSpideringResult(String spideringId){
        Optional<Scanning> scanning = this.scanningRepository.findById(spideringId);
        return scanning.orElse(null);

    }

    public void saveReport(List<ActiveScanResponse>analysisResultList){
        analysisResultList.stream().forEach(element ->
                this.scanningRepository.save(new Scanning(element.getSite().get(0).getName(), element.getSite())));
        this.logger.info("save results");
    }

}
