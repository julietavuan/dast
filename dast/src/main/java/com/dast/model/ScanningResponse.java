package com.dast.model;
import java.util.List;

public class ScanningResponse {
    private String url;
    private List<ActiveScan> activeScanResponseList;

    public ScanningResponse() {
    }

    public ScanningResponse(String url, List<ActiveScan> activeScanResponseList) {
        this.url = url;
        this.activeScanResponseList = activeScanResponseList;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public List<ActiveScan> getActiveScanResponseList() {
        return activeScanResponseList;
    }

    public void setActiveScanResponseList(List<ActiveScan> activeScanResponseList) {
        this.activeScanResponseList = activeScanResponseList;
    }
}
