package com.dast.service;

import com.dast.exception.NoScanningFoundException;
import com.dast.model.Scanning;
import com.dast.streaming.model.ScanningResponse;
import com.dast.streaming.publisher.StreamingPublisher;
import com.dast.repository.ScanningRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Date;

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

    public Scanning publish(String url){
        Scanning scanning = this.scanningRepository.findByUrl(url);
        if((scanning == null)){
            scanning = new Scanning(url);
            this.scanningRepository.save(scanning);
            this.streamingPublisher.publish(scanning.getUrl());
        } else if(shouldScanAgain(scanning)) this.streamingPublisher.publish(scanning.getUrl());
        return scanning;
    }

    private boolean shouldScanAgain(Scanning scanning){
        return isOldScanning(scanning) || scanning.haveFail();
    }

    private boolean isOldScanning(Scanning scanning){
        Date now = new Date();
        return (scanning!=null)&&
                // Verificar la comparación. Ajustarla para que compare por dias
                ((scanning.getTime().compareTo(now) > 0) &&
                        (scanning.getState().equalsIgnoreCase("Done")));
    }

    public Scanning getSpideringResult(String spideringId){
            Optional<Scanning> scanning = this.scanningRepository.findById(spideringId);
        return scanning.orElseThrow(NoScanningFoundException::new);

    }

    /**
     * This method will update the corresponding scanning every time it has any response
     * If Response is null or we can't find a corresponding scanning we will report to #App
     *
     */
    public void updateScanning(ScanningResponse scanningResponse){
        Scanning scanning = this.scanningRepository.findByUrl(scanningResponse.getUrl());
        if(scanning != null){
                scanning.setActiveScanResponses(scanningResponse.getActiveScanResponseList());
                if(scanningResponse.succeed()){
                    scanning.setStateToDone();
                }else{
                    scanning.setStateToFail();
                }
                this.scanningRepository.save(scanning);
                this.logger.info("results saved");
        }else{
            // Report to Statistics app (new relic)
            // We could throw an exception. Is debatable
            logger.error("No scanning found for url: "+ scanningResponse.getUrl());
        }
    }

    public void failPublishScan(String url) {
        Scanning scanning = this.scanningRepository.findByUrl(url);
        if(scanning != null){
            scanning.setStateToFail();
            this.scanningRepository.save(scanning);
        }else{
            // Report to Statistics app (new relic)
            // We could throw an exception. Is debatable
            logger.error("No scanning found for url: "+ url);
        }
    }
}
