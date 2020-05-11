package com.dast.model;

import java.util.List;

public class UrlSpidering {
    private List<Site> site;

    public UrlSpidering(List<Site> site) {
        this.site = site;
    }

    public UrlSpidering() {
    }

    public List<Site> getSite() {
        return site;
    }

    public void setSite(List<Site> site) {
        this.site = site;
    }
}
