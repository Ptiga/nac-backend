package com.socgen.nac.entity;

//import org.apache.commons.io.FilenameUtils;

import org.springframework.beans.factory.annotation.Value;

public class Statement {

    @Value("${dataSeparator}")
    private Character dataSeparator;

    private String filename;
    private String statementType;
    private String userTag;
    private String fund;
    private String navDate;
    private String fileTimestamp;
    private String fileExtension;
    private int numberOfSeparators;


    public Statement() {
    }

    public Statement(String filename) {
        this.filename = filename;
        this.setStatementType();
        this.setFileExtension();
        this.setNumberOfSeparator();
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

    public void setFileTimestamp() {
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
            if (charFound == '_')
                numberOfSeparators++;
        }
        this.numberOfSeparators = numberOfSeparators;
    }
}
