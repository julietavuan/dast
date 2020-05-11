package com.example.zap.model;

public class Alert {
    String name;
    String riskcode;
    String riskdesc;
    String desc;
    String solution;
    String reference;

    public Alert() {
    }

    public Alert(String name, String riskcode, String riskdesc, String desc, String solution, String reference) {
        this.name = name;
        this.riskcode = riskcode;
        this.riskdesc = riskdesc;
        this.desc = desc;
        this.solution = solution;
        this.reference = reference;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRiskcode() {
        return riskcode;
    }

    public void setRiskcode(String riskcode) {
        this.riskcode = riskcode;
    }

    public String getRiskdesc() {
        return riskdesc;
    }

    public void setRiskdesc(String riskdesc) {
        this.riskdesc = riskdesc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getSolution() {
        return solution;
    }

    public void setSolution(String solution) {
        this.solution = solution;
    }

    public String getReference() {
        return reference;
    }

    public void setReference(String reference) {
        this.reference = reference;
    }
}
