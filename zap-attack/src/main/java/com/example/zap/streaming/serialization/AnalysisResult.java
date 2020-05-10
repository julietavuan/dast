package com.example.zap.streaming.serialization;

public class AnalysisResult {
    private Long id;
    private String url;
    private Integer totalVulnerabilities;
    private String vulnerabilities;

    public AnalysisResult() {
    }

    public AnalysisResult(String vulnerability, String url) {
        this.vulnerabilities = vulnerability;
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

    public Integer getTotalVulnerabilities() {
        return totalVulnerabilities;
    }

    public void setTotalVulnerabilities(Integer totalVulnerabilities) {
        this.totalVulnerabilities = totalVulnerabilities;
    }

    public String getVulnerabilities() {
        return vulnerabilities;
    }

    public void setVulnerabilities(String vulnerabilities) {
        this.vulnerabilities = vulnerabilities;
    }
}
