package com.dast.model;

import org.springframework.hateoas.RepresentationModel;
import java.util.List;

public class ScanningModel extends RepresentationModel<ScanningModel> {
    private String url;
    private String id;
    private String state;
    private List<ActiveScan> activeScanResponses;

    public ScanningModel(String url, String id, List<ActiveScan>activeScanResponses, String state) {
        this.url = url;
        this.id = id;
        this.activeScanResponses = activeScanResponses;
        this.state = state;
    }
    public ScanningModel(String url, String id, String state) {
        this.url = url;
        this.id = id;
        this.state = state;
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
