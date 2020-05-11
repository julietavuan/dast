package com.dast.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Document(collection = "scannings")
public class Scanning {
    @Id
    private ObjectId id = new ObjectId();
    private String url;
    private Date time;
    private List<ActiveScan> activeScanResponses;

    private String state;

    public Scanning(String url) {
        this.url = url;
        this.time = new Date();
        this.state = "Processing";
        this.activeScanResponses = new ArrayList<ActiveScan>();
    }

    public Scanning() {
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
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
