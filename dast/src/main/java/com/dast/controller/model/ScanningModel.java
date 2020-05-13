package com.dast.controller.model;

import org.springframework.hateoas.RepresentationModel;

public class ScanningModel extends RepresentationModel<ScanningModel> {
    private String url;
    private String id;
    private String state;


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

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
