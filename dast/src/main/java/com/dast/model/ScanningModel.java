package com.dast.model;

import org.springframework.hateoas.RepresentationModel;

public class ScanningModel extends RepresentationModel<ScanningModel> {
    private String url;
    private Long id;

    public ScanningModel(String url, Long id) {
        this.url = url;
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
