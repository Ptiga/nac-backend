package com.socgen.nac.entity.result;

import com.socgen.nac.entity.source.Invcah;
import com.socgen.nac.entity.source.Jourop;
import com.socgen.nac.entity.source.Vinvca;

public class Result {

    private Long id;
    private String codeFonds;
    private String nomFonds;
    private String navDate;
    private String controlName;
    private String securityCode;
    private String securityLabel;
    private String priorDatePrice;
    private double priorPrice;
    private String priorCurrency;
    private String currentDatePrice;
    private double currentPrice;
    private String currentCurrency;
    private double fluctuation;
    private double threshold;
    private String alertType;
    private String operatorComment;
    private String supervisorComment;
    private boolean isResultValidated;


    public Result(Invcah invcah, double fluctuation, double threshold, String alertType) {
        this.codeFonds = invcah.getCodeFonds();
        this.nomFonds = invcah.getNomfonds();
        this.navDate = invcah.getDateVL();
        setControlName();
        this.securityCode = invcah.getIsinValeur();
        this.securityLabel = invcah.getLibeleValeur();
        this.currentDatePrice = invcah.getDateCours();
        this.currentPrice = invcah.getCours();
        this.currentCurrency = invcah.getDeviseCours();
        this.fluctuation = fluctuation;
        this.threshold = threshold;
        this.alertType = alertType;
        this.isResultValidated = false;
    }

    public String getCodeFonds() {
        return codeFonds;
    }

    public void setCodeFonds(String codeFonds) {
        this.codeFonds = codeFonds;
    }

    public String getNomFonds() {
        return nomFonds;
    }

    public void setNomFonds(String nomFonds) {
        this.nomFonds = nomFonds;
    }

    public String getNavDate() {
        return navDate;
    }

    public void setNavDate(String navDate) {
        this.navDate = navDate;
    }

    public String getControlName() {
        return controlName;
    }

    public void setControlName() {
        this.controlName = "Price_Fluctuation";
    }

    public String getSecurityCode() {
        return securityCode;
    }

    public void setSecurityCode(String securityCode) {
        this.securityCode = securityCode;
    }

    public String getSecurityLabel() {
        return securityLabel;
    }

    public void setSecurityLabel(String securityLabel) {
        this.securityLabel = securityLabel;
    }

    public String getPriorDatePrice() {
        return priorDatePrice;
    }

    public void setPriorDatePrice(String priorDatePrice) {
        this.priorDatePrice = priorDatePrice;
    }

    public double getPriorPrice() {
        return priorPrice;
    }

    public void setPriorPrice(double priorPrice) {
        this.priorPrice = priorPrice;
    }

    public String getPriorCurrency() {
        return priorCurrency;
    }

    public void setPriorCurrency(String priorCurrency) {
        this.priorCurrency = priorCurrency;
    }

    public String getCurrentDatePrice() {
        return currentDatePrice;
    }

    public void setCurrentDatePrice(String currentDatePrice) {
        this.currentDatePrice = currentDatePrice;
    }

    public double getCurrentPrice() {
        return currentPrice;
    }

    public void setCurrentPrice(double currentPrice) {
        this.currentPrice = currentPrice;
    }

    public String getCurrentCurrency() {
        return currentCurrency;
    }

    public void setCurrentCurrency(String currentCurrency) {
        this.currentCurrency = currentCurrency;
    }

    public double getFluctuation() {
        return fluctuation;
    }

    public void setFluctuation(double fluctuation) {
        this.fluctuation = fluctuation;
    }

    public double getThreshold() {
        return threshold;
    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }

    public String getAlertType() {
        return alertType;
    }

    public void setAlertType(String alertType) {
        this.alertType = alertType;
    }

    public String getOperatorComment() {
        return operatorComment;
    }

    public void setOperatorComment(String operatorComment) {
        this.operatorComment = operatorComment;
    }

    public String getSupervisorComment() {
        return supervisorComment;
    }

    public void setSupervisorComment(String supervisorComment) {
        this.supervisorComment = supervisorComment;
    }

    public boolean isResultValidated() {
        return isResultValidated;
    }

    public void setResultValidated(boolean resultValidated) {
        isResultValidated = resultValidated;
    }


    public void addVinvcaAttributes(Vinvca vinvca) {
        setPriorDatePrice(vinvca.getDateCours());
        setPriorPrice(vinvca.getCours());
        setPriorCurrency(vinvca.getDeviseCours());
    }

    public void addJouropAttributes(Jourop jourop) {
        setPriorDatePrice(jourop.getTradeDate());
        setPriorPrice(jourop.getTradePrice());
        setPriorCurrency(jourop.getTradeCurrency());
    }

}
