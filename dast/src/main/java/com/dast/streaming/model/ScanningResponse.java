package com.dast.streaming.model;
import com.dast.model.ActiveScan;

import java.util.List;

public class ScanningResponse {
    private String url;
    private List<ActiveScan> activeScanResponseList;
    private ScanningResponseState state;

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

    public boolean succeed() {
        return ScanningResponseState.SUCCESS.equals(state);
    }

    public boolean failed() {
        return ScanningResponseState.FAIL.equals(state);
    }
}
