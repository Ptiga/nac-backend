package com.socgen.nac.entity.source;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Jourop{

    @Id
    @Column(name = "code_operation")
    private String codeOperation;
    @Column(name = "source_filename")
    private String sourceFilename;
    @Column(name = "code_fonds")
    private String codeFonds;
    private String categorie;
    @Column(name = "transaction_type")
    private String transactionType;
    @Column(name = "isin_valeur")
    private String isinValeur;
    @Column(name = "date_transaction")
    private String tradeDate;
    @Column(name = "cours_transaction")
    private double tradePrice;
    @Column(name = "devise_transaction")
    private String tradeCurrency;


    public Jourop(String codeOperation, String sourceFilename, String codeFonds, String categorie, String transactionType, String isinValeur, String tradeDate, double tradePrice, String deviseCours) {
        this.codeOperation = codeOperation;
        this.sourceFilename = sourceFilename;
        this.codeFonds = codeFonds;
        this.categorie = categorie;
        this.transactionType = transactionType;
        this.isinValeur = isinValeur;
        this.tradeDate = tradeDate;
        this.tradePrice = tradePrice;
        this.tradeCurrency = deviseCours;
    }

    public Jourop() {
    }


    public String getCodeOperation() {
        return codeOperation;
    }

    public void setCodeOperation(String codeOperation) {
        this.codeOperation = codeOperation;
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

    public String getTradeCurrency() {
        return tradeCurrency;
    }

    public void setTradeCurrency(String tradeCurrency) {
        this.tradeCurrency = tradeCurrency;
    }
}
