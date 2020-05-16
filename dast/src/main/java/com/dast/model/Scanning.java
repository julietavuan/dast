package com.dast.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Document(collection = "scannings")
public class Scanning {
    @Id
    private ObjectId id = new ObjectId();
    private String url;
    private LocalDate time;
    private List<ActiveScan> activeScanResponses;

    private ScanningState state;

    public Scanning(String url) {
        this.url = url;
        this.time = LocalDate.now();
        this.state = ScanningState.PROCESSING;
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

    public LocalDate getTime() {
        return time;
    }

    public void setTime(LocalDate time) {
        this.time = time;
    }

    public List<ActiveScan> getActiveScanResponses() {
        return activeScanResponses;
    }

    public void setActiveScanResponses(List<ActiveScan> activeScanResponses) {
        this.activeScanResponses = activeScanResponses;
    }

    public String getState() {
        return this.state.toString();
    }

    public void setStateToFail(){
        this.state = ScanningState.FAIL;
    }

    public void setStateToProcessing(){
        this.state = ScanningState.PROCESSING;
    }

    public void setStateToDone(){
        this.state = ScanningState.DONE;
    }

    public boolean haveFail() {
        return ScanningState.FAIL.equals(this.state);
    }
}
