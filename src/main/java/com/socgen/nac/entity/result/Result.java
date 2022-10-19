package com.socgen.nac.entity.result;

import com.socgen.nac.entity.source.Invcah;
import com.socgen.nac.entity.source.Jourop;
import com.socgen.nac.entity.source.Threshold;
import com.socgen.nac.entity.source.Vinvca;

import javax.persistence.*;

//@tablename("xx") -> Permettre d'utiliser un nom de table différent dans la BDD

@Entity
public class Result {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "code_fonds")
    private String codeFonds;
    @Column(name = "nom_fonds")
    private String nomFonds;
    @Column(name = "nav_date")
    private String navDate;
    @Column(name = "nom_controle")
    private String controlName;
    @Column(name = "code_isin")
    private String securityCode;
    @Column(name = "libelle_valeur")
    private String securityLabel;
    @Column(name = "type_instrument")
    private String securityType;
    @Column(name = "date_cours_veille")
    private String priorDatePrice;
    @Column(name = "prix_veille")
    private double priorPrice;
    @Column(name = "devise_veille")
    private String priorCurrency;
    @Column(name = "date_cours_jour")
    private String currentDatePrice;
    @Column(name = "cours_jour")
    private double currentPrice;
    @Column(name = "devise_jour")
    private String currentCurrency;
    private double fluctuation;
    @Column(name = "seuil")
    private double threshold;
    @Column(name = "alerte")
    private String alertType;
    @Column(name = "commentaire_comptable")
    private String operatorComment;
    @Column(name = "commentaire_superviseur")
    private String supervisorComment;
    @Column(name = "validation")
    private boolean isResultValidated;
    @Column(name = "active")
    private boolean isActivatedLine;


    public Result(Invcah invcah, double fluctuation, Threshold threshold, String alertType) {
        this.codeFonds = invcah.getCodeFonds();
        this.nomFonds = invcah.getNomfonds();
        this.navDate = invcah.getDateVL();
        setControlName();
        this.securityCode = invcah.getIsinValeur();
        this.securityLabel = invcah.getLibeleValeur();
        setSecurityType(threshold);
        this.currentDatePrice = invcah.getDateCours();
        this.currentPrice = invcah.getCours();
        this.currentCurrency = invcah.getDeviseCours();
        setFluctuation(fluctuation);
        setThreshold(threshold.getThreshold());
        this.alertType = alertType;
        this.isResultValidated = false;
        this.isActivatedLine = true;
    }

    public Result() {

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public void setControlName(String controlName) {
        this.controlName = controlName;
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

    public String getSecurityType() {
        return securityType;
    }

    public void setSecurityType(String securityType) {
        this.securityType = securityType;
    }

    public void setSecurityType(Threshold Threshold) {
        this.securityType = Threshold.getTypeInstrument();
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
        this.fluctuation = convertToTwoDecimal(fluctuation);
    }

    public double getThreshold() {
        return threshold;
    }

    public void setThreshold(double threshold) {
        this.threshold = convertToTwoDecimal(threshold);
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

    public boolean isActivatedLine() {
        return isActivatedLine;
    }

    public void setActivatedLine(boolean activatedLine) {
        isActivatedLine = activatedLine;
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

    private double convertToTwoDecimal(double nombre){
        return Math.round(nombre*10000.0)/100.0;
    }

}
