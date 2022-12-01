package com.mabanque.nac.entity.source;

import javax.persistence.*;

@Entity
public class Invcah{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "source_filename")
    private String sourceFilename;
    @Column(name = "code_fonds")
    private String codeFonds;
    @Column(name = "nom_fonds")
    private String nomfonds;
    @Column(name = "devise_fonds")
    private String deviseFonds;
    @Column(name = "date_vl")
    private String dateVL;
    @Column(name = "tri_comptable")
    private String triComptable;
    private String categorie;
    @Column(name = "isin_valeur")
    private String isinValeur;
    @Column(name = "libelle_valeur")
    private String libeleValeur;
    @Column(name = "date_cours")
    private String dateCours;
    private double cours;
    @Column(name = "devise_cours")
    private String deviseCours;


    public Invcah() {
    }

    public Invcah(String sourceFilename, String codeFonds, String nomfonds, String deviseFonds, String dateVL, String triComptable, String categorie, String isinValeur, String libeleValeur, String dateCours, double cours, String deviseCours) {
        this.sourceFilename = sourceFilename;
        this.codeFonds = codeFonds;
        this.nomfonds = nomfonds;
        this.deviseFonds = deviseFonds;
        this.dateVL = dateVL;
        this.triComptable = triComptable;
        this.categorie = categorie;
        this.isinValeur = isinValeur;
        this.libeleValeur = libeleValeur;
        this.dateCours = dateCours;
        this.cours = cours;
        this.deviseCours = deviseCours;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public String getTriComptable() {
        return triComptable;
    }

    public void setTriComptable(String triComptable) {
        this.triComptable = triComptable;
    }

    public String getCategorie() {
        return categorie;
    }

    public void setCategorie(String categorie) {
        this.categorie = categorie;
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
