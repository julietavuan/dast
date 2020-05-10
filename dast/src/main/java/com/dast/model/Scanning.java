package com.dast.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.ZonedDateTime;

@Document(collection = "scannings")
public class Scanning {
    @Id
    private Long id;
    private String url;
    private ZonedDateTime time;

    public Scanning(String url, ZonedDateTime time) {
        this.url = url;
        this.time = time;
    }

    public Scanning() {
    }
    public Scanning(String url){
        this.url = url;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ZonedDateTime getTime() {
        return time;
    }

    public void setTime(ZonedDateTime time) {
        this.time = time;
    }
}
