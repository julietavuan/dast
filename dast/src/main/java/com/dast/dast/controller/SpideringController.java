package com.dast.dast.controller;

import com.dast.dast.dao.RequestScanning;
import com.dast.dast.model.Scanning;
import com.dast.dast.service.SpideringService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SpideringController {

    @Autowired
    private SpideringService spideringService;


    public SpideringController() {
    }

    @PostMapping("/scanning")
    Scanning newScanning(@RequestBody RequestScanning newScanning){
        this.spideringService.publish(newScanning);
        return null;
    }


}
