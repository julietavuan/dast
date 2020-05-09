package com.dast.dast.dao;

public class RequestScanning {
    private String url;

    public RequestScanning(String url) {
        this.url = url;
    }

    public RequestScanning() {
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
