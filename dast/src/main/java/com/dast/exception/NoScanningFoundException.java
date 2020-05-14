package com.dast.exception;

public class NoScanningFoundException extends RuntimeException{

    public NoScanningFoundException() {
        super("No Scanning found");
    }
}
