package com.dast.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;


public class Site {
    @JsonProperty("@name")
    private String name;
    @JsonProperty("@host")
    private String host;
    private List<Alert> alerts;

    public Site() {

    }

    public Site(String name, String host, List<Alert> alerts) {
        this.name = name;
        this.host = host;
        this.alerts = alerts;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public List<Alert> getAlerts() {
        return alerts;
    }

    public void setAlerts(List<Alert> alerts) {
        this.alerts = alerts;
    }
}
