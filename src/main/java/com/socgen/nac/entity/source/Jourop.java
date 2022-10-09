package com.socgen.nac.entity.source;

public class Jourop{

    private String sourceFilename;
    private String codeFonds;
    private String categorie;
    private String transactionType;
    private String isinValeur;
    private String tradeDate;
    private double tradePrice;
    private String deviseCours;


    public Jourop(String sourceFilename, String codeFonds, String categorie, String transactionType, String isinValeur, String tradeDate, double tradePrice, String deviseCours) {
        this.sourceFilename = sourceFilename;
        this.codeFonds = codeFonds;
        this.categorie = categorie;
        this.transactionType = transactionType;
        this.isinValeur = isinValeur;
        this.tradeDate = tradeDate;
        this.tradePrice = tradePrice;
        this.deviseCours = deviseCours;
    }


    public String getSourceFilename() {
        return sourceFilename;
    }

    public void setSourceFilename(String sourceFilename) {
        this.sourceFilename = sourceFilename;
    }

    public String getCodeFonds() {
        return codeFonds;
    }

    public void setCodeFonds(String codeFonds) {
        this.codeFonds = codeFonds;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
    }

    public String getTransactionType() {
        return transactionType;
    }

    public void setTransactionType(String transactionType) {
        this.transactionType = transactionType;
    }

    public String getIsinValeur() {
        return isinValeur;
    }

    public void setIsinValeur(String isinValeur) {
        this.isinValeur = isinValeur;
    }

    public String getTradeDate() {
        return tradeDate;
    }

    public void setTradeDate(String tradeDate) {
        this.tradeDate = tradeDate;
    }

    public double getTradePrice() {
        return tradePrice;
    }

    public void setTradePrice(double tradePrice) {
        this.tradePrice = tradePrice;
    }

    public String getDeviseCours() {
        return deviseCours;
    }

    public void setDeviseCours(String deviseCours) {
        this.deviseCours = deviseCours;
    }
}
