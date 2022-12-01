package com.mabanque.nac.entity.source;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Fund {

    @Id
    @Column(name = "fund_code")
    private String fundCode;
    @Column(name = "fund_name")
    private String fundName;
    @Column(name = "nav_frequency")
    private char navFrequency;
    @Column(name = "valuation_team")
    private String valuationTeam;


    public Fund(String fundCode, String fundName, char navFrequency, String valuationTeam) {
        this.fundCode = fundCode;
        this.fundName = fundName;
        this.navFrequency = navFrequency;
        this.valuationTeam = valuationTeam;
    }

    public Fund() {
    }


    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }

    public String getFundName() {
        return fundName;
    }

    public void setFundName(String fundName) {
        this.fundName = fundName;
    }

    public char getNavFrequency() {
        return navFrequency;
    }

    public void setNavFrequency(char navFrequency) {
        this.navFrequency = navFrequency;
    }

    public String getValuationTeam() {
        return valuationTeam;
    }

    public void setValuationTeam(String valuationTeam) {
        this.valuationTeam = valuationTeam;
    }
}
