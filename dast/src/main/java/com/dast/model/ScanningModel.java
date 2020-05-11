package com.dast.model;

import org.springframework.hateoas.RepresentationModel;
import java.util.List;

public class ScanningModel extends RepresentationModel<ScanningModel> {
    private String url;
    private String id;
    private List<Site> sites;

    public ScanningModel(String url, String id, List<Site>sites) {
        this.url = url;
        this.id = id;
        this.sites = sites;
    }
    public ScanningModel(String url, String id) {
        this.url = url;
        this.id = id;
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

    public List<Site> getSites() {
        return sites;
    }

    public void setSites(List<Site> sites) {
        this.sites = sites;
    }
}
