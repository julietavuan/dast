package com.dast.controller.model;

import com.dast.model.ActiveScan;

import java.util.List;

public class SpideringModel {
    private String url;
    private String id;
    private String state;
    private List<ActiveScan> activeScanResponses;

    public SpideringModel(String url, String id, String state,List<ActiveScan> activeScanResponses ) {
        this.url = url;
        this.id = id;
        this.state = state;
        this.activeScanResponses = activeScanResponses;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<ActiveScan> getActiveScanResponses() {
        return activeScanResponses;
    }

    public void setActiveScanResponses(List<ActiveScan> activeScanResponses) {
        this.activeScanResponses = activeScanResponses;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
