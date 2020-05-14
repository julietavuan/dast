package com.dast.controller;

import com.dast.dao.RequestScanning;
import com.dast.model.Scanning;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.dast.service.SpideringService;
import org.apache.commons.validator.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.apache.commons.validator.UrlValidator.ALLOW_ALL_SCHEMES;

@RequestMapping("dast")
@RestController
public class SpideringController {
    private final Logger logger = LoggerFactory.getLogger(SpideringController.class);

    @Autowired
    private SpideringService spideringService;

    @Autowired
    private DastResponse dastResponse;


    public SpideringController() {
    }

    @PostMapping("/scanning")
    ResponseEntity newScanning(@RequestBody RequestScanning newScanning){
        UrlValidator urlValidator = new UrlValidator(ALLOW_ALL_SCHEMES);
        if (urlValidator.isValid(newScanning.getUrl())) {
            Scanning scanning = this.spideringService.publish(newScanning.getUrl());
            return this.dastResponse.responseEntityScanning(scanning);
        }
        else{
            return this.dastResponse.responseEntityBadUrl();
        }

    }

    @GetMapping("/spiderings/{spidering-id}/results")
    ResponseEntity resultScanning(@PathVariable("spidering-id") String spideringId){
        Scanning scanning = this.spideringService.getSpideringResult(spideringId);
        return this.dastResponse.responseEntitySpidering(scanning);
    }



}
