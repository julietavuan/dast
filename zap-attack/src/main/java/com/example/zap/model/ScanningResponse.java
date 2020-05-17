package com.example.zap.model;
import java.util.List;

public class ScanningResponse {
    private String url;
    private List<ActiveScan> activeScanResponseList;
    private ScanningState state;

    public ScanningResponse() {
    }

    public ScanningResponse(String url) {
        this.url = url;
    }

    public ScanningResponse(String url, List<ActiveScan> activeScanResponseList, ScanningState scanningState) {
        this.url = url;
        this.activeScanResponseList = activeScanResponseList;
        this.state = scanningState;
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

    public void succeed() {
        this.state = ScanningState.SUCCESS;
    }

    public void fail() {
        this.state = ScanningState.FAIL;
    }

   public ScanningState getState() {
       return state;
   }
}
