package com.dast.controller;

import com.dast.dao.RequestScanning;
import com.dast.model.Scanning;
import com.dast.model.ScanningModel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.hateoas.EntityModel;
import com.dast.service.SpideringService;
import org.apache.commons.validator.UrlValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.apache.commons.validator.UrlValidator.ALLOW_ALL_SCHEMES;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
public class SpideringController {
    private final Logger logger = LoggerFactory.getLogger(SpideringController.class);


    @Autowired
    private SpideringService spideringService;


    public SpideringController() {
    }

    @PostMapping("/scanning")
    ResponseEntity newScanning(@RequestBody RequestScanning newScanning){
        UrlValidator urlValidator = new UrlValidator(ALLOW_ALL_SCHEMES);
        if (urlValidator.isValid(newScanning.getUrl())) {
            Scanning scanning = this.spideringService.getScanningByUrl(newScanning.getUrl());
            scanning = this.spideringService.scanningUpdated(scanning);
            if(scanning == null){
                scanning = this.spideringService.publish(newScanning);
            }
            ScanningModel scanningModel = new ScanningModel(scanning.getUrl(),scanning.getId().toHexString(), scanning.getState());
            EntityModel entityModel = new EntityModel(scanningModel,
                    linkTo(methodOn(SpideringController.class).resultScanning(scanningModel.getId())).withSelfRel());
            return ResponseEntity.accepted().body(entityModel);
        }
        else{
            return ResponseEntity.badRequest().body("Bad URL");
        }

    }

    @GetMapping("/spiderings/{spidering-id}/results")
    ResponseEntity resultScanning(@PathVariable("spidering-id") String spideringId){
        Scanning scanning = this.spideringService.getSpideringResult(spideringId);
        if(scanning != null){
            ScanningModel scanningModel = new ScanningModel(scanning.getUrl(),scanning.getId().toHexString(),
                    scanning.getActiveScanResponses(),scanning.getState() );
            EntityModel entityModel = new EntityModel(scanningModel,
                    linkTo(methodOn(SpideringController.class).resultScanning(scanningModel.getId())).withSelfRel());
            return ResponseEntity.accepted().body(entityModel);
        }
        else{
            return ResponseEntity.badRequest().body("Id does not match with Scanned URLs");
        }
    }

}