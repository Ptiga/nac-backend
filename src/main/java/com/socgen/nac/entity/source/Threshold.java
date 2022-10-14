package com.socgen.nac.entity.source;

public class Threshold {

    private String triComptable;
    private String typeInstrument;
    private double thresholdRate;

    public Threshold(String triComptable, String typeInstrument, double threshold) {
        this.triComptable = triComptable;
        this.typeInstrument = typeInstrument;
        this.thresholdRate = threshold;
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

    public double getThresholdRate() {
        return thresholdRate;
    }

    public void setThresholdRate(double thresholdRate) {
        this.thresholdRate = thresholdRate;
    }

}
