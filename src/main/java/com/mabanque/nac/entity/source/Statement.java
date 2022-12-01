package com.mabanque.nac.entity.source;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class Statement {

    //@Transient
    @Column(name="data_separator")
    private Character dataSeparator;
    //private final Character dataSeparator;
    //@Transient
    @Column(name = "number_expected")
    private int numberOfSeparatorExpected;
    //private final int numberOfSeparatorExpected;

    @Id
    private String filename;
    @Column(name = "statement_type")
    private String statementType;
    @Column(name = "user_tag")
    private String userTag;
    private String fund;
    @Column(name = "nav_date")
    private String navDate;
    @Column(name = "file_timestamp")
    private String fileTimestamp;
    @Column(name = "file_extension")
    private String fileExtension;
    @Column(name = "number_of_separators")
    private int numberOfSeparators;


    public Statement(String filename, char dataSeparator, int numberOfSeparatorExpected) {
        this.filename = filename;
        this.setStatementType();
        this.setFileExtension();
        this.dataSeparator = dataSeparator;
        this.setNumberOfSeparator();
        this.numberOfSeparatorExpected = numberOfSeparatorExpected;
    }

    public Statement() {
    }


    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getStatementType() {
        return statementType;
    }

    public void setStatementType() {
        this.statementType = this.filename.substring(0,6);
    }

    public String getUserTag() {
        return userTag;
    }

    public void setUserTag(String userTag) {
        this.userTag = userTag;
    }

    public String getFund() {
        return fund;
    }

    public void setFund(String fund) {
        this.fund = fund;
    }

    public String getNavDate() {
        return navDate;
    }

    public void setNavDate(String navDate) {
        this.navDate = navDate;
    }

    public String getFileTimestamp() {
        return fileTimestamp;
    }

    public void setFileTimestamp(String fileTimestamp) {
        this.fileTimestamp = fileTimestamp;
    }

    public String getFileExtension() {
        return fileExtension;
    }

    public void setFileExtension() {
        this.fileExtension = this.filename.substring(this.filename.lastIndexOf('.')+1);
    }

    public int getNumberOfSeparators() {
        return numberOfSeparators;
    }

    public void setNumberOfSeparator() {
        int numberOfSeparators = 0;
        char charFound;
        for (int i = 0; i < this.filename.length(); i++) {
            charFound = this.filename.charAt(i);
            if (charFound == dataSeparator)
            //if (charFound == '_')
                numberOfSeparators++;
        }
        this.numberOfSeparators = numberOfSeparators;
    }

    public Character getDataSeparator() {
        return dataSeparator;
    }

    public int getNumberOfSeparatorExpected() {
        return numberOfSeparatorExpected;
    }

    public boolean isStatementContainsHeader(){
        switch (this.statementType){
            case "invcah":
            case "vinvca":
                return true;
            case "jourop":
                return false;
            default:
                return false;
        }
    }
}
