package com.socgen.nac.entity;

public class Vinvca {

    private String sourceFilename;
    private String codeFonds;
    private String nomfonds;
    private String deviseFonds;
    private String dateVL;
    private String isinValeur;
    private String libeleValeur;
    private String dateCours;
    private double cours;
    private String deviseCours;


    public Vinvca() {
    }

    public Vinvca(String sourceFilename, String codeFonds, String nomfonds, String deviseFonds, String dateVL, String isinValeur, String libeleValeur, String dateCours, double cours, String deviseCours) {
        this.sourceFilename = sourceFilename;
        this.codeFonds = codeFonds;
        this.nomfonds = nomfonds;
        this.deviseFonds = deviseFonds;
        this.dateVL = dateVL;
        this.isinValeur = isinValeur;
        this.libeleValeur = libeleValeur;
        this.dateCours = dateCours;
        this.cours = cours;
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

    public String getNomfonds() {
        return nomfonds;
    }

    public void setNomfonds(String nomfonds) {
        this.nomfonds = nomfonds;
    }

    public String getDeviseFonds() {
        return deviseFonds;
    }

    public void setDeviseFonds(String deviseFonds) {
        this.deviseFonds = deviseFonds;
    }

    public String getDateVL() {
        return dateVL;
    }

    public void setDateVL(String dateVL) {
        this.dateVL = dateVL;
    }

    public String getIsinValeur() {
        return isinValeur;
    }

    public void setIsinValeur(String isinValeur) {
        this.isinValeur = isinValeur;
    }

    public String getLibeleValeur() {
        return libeleValeur;
    }

    public void setLibeleValeur(String libeleValeur) {
        this.libeleValeur = libeleValeur;
    }

    public String getDateCours() {
        return dateCours;
    }

    public void setDateCours(String dateCours) {
        this.dateCours = dateCours;
    }

    public double getCours() {
        return cours;
    }

    public void setCours(double cours) {
        this.cours = cours;
    }

    public String getDeviseCours() {
        return deviseCours;
    }

    public void setDeviseCours(String deviseCours) {
        this.deviseCours = deviseCours;
    }
}

