package com.dast.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class ControllerAdvisor {

    @ExceptionHandler(NoScanningFoundException.class)
    public ResponseEntity handleNoScanningFound(NoScanningFoundException exception, WebRequest request){
        return ResponseEntity.notFound().build();
    }

}
