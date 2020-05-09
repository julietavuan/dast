package com.dast.dast.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "scannings")
public class Scanning {
    @Id
    private String url;

    public Scanning() {
    }
    public Scanning(String url){
        this.url = url;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
