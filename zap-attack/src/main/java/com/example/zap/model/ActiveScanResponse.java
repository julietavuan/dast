package com.example.zap.model;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class ActiveScanResponse {
    @JsonProperty("@version")
    String version;
    List<Site> site;

    public ActiveScanResponse() {
    }

    public ActiveScanResponse(String version, List<Site> site) {
        this.version = version;
        this.site = site;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<Site> getSite() {
        return site;
    }

    public void setSite(List<Site> site) {
        this.site = site;
    }

    @Override
    public String toString() {
        return (version + site.size());
    }
}
