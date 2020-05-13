package com.dast.controller;

import com.dast.model.Scanning;
import com.dast.model.ScanningModel;
import com.dast.model.SpideringModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestControllerAdvice
public class DastResponse {


    public ResponseEntity responseEntityScanning(Scanning scanning){
        ScanningModel scanningModel = new ScanningModel(scanning.getUrl(),scanning.getId().toHexString(),
                scanning.getState() );
        EntityModel entityModel = new EntityModel(scanningModel,
                linkTo(methodOn(SpideringController.class).resultScanning(scanningModel.getId())).withSelfRel());
        return ResponseEntity.accepted().body(entityModel);
    }

    public ResponseEntity responseEntitySpidering(Scanning scanning){
        SpideringModel spideringModel = new SpideringModel(scanning.getUrl(),scanning.getId().toHexString(),
                scanning.getState(), scanning.getActiveScanResponses() );
        EntityModel entityModel = new EntityModel(spideringModel,
                linkTo(methodOn(SpideringController.class).resultScanning(spideringModel.getId())).withSelfRel());
        return ResponseEntity.accepted().body(entityModel);
    }

    public ResponseEntity responseEntityBadUrl(){
        return ResponseEntity.badRequest().body("Bad URL");
    }

    public ResponseEntity responseEntityBadId(){
        return ResponseEntity.badRequest().body("Id does not match with Scanned URLs");
    }
}
