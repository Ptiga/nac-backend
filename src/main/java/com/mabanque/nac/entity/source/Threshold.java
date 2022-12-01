package com.mabanque.nac.entity.source;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Threshold {

    @Id
    @Column(name = "tri_comptable")
    private String triComptable;
    @Column(name = "type_instrument")
    private String typeInstrument;
    private double threshold;


    public Threshold() {
    }

    public Threshold(String triComptable, String typeInstrument, double threshold) {
        this.triComptable = triComptable;
        this.typeInstrument = typeInstrument;
        this.threshold = threshold;
    }




    public String getTriComptable() {
        return triComptable;
    }

    public void setTriComptable(String triComptable) {
        this.triComptable = triComptable;
    }

    public String getTypeInstrument() {
        return typeInstrument;
    }

    public void setTypeInstrument(String typeInstrument) {
        this.typeInstrument = typeInstrument;
    }

    public double getThreshold() {
        return threshold;
    }

    public void setThreshold(double threshold) {
        this.threshold = threshold;
    }

}
