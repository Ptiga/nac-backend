package com.socgen.nac.entity.check;

import com.socgen.nac.entity.source.Invcah;
import com.socgen.nac.entity.source.Jourop;
import com.socgen.nac.entity.source.Vinvca;

public class CheckFluctuationData {

    private Invcah invcah;
    private Vinvca vinvca;
    private Jourop jourop;
    private double fluctuation;
    private double threshold;
    private String alertType;


    public CheckFluctuationData(Invcah invcah, Vinvca vinvca, double threshold) {
        this.invcah = invcah;
        this.vinvca = vinvca;
        setFluctuation();
        this.threshold = threshold;
        setAlertType();
    }

    public CheckFluctuationData(Invcah invcah, Jourop jourop, double threshold) {
        this.invcah = invcah;
        this.jourop = jourop;
        setFluctuation();
        this.threshold = threshold;
        setAlertType();
    }


    public Invcah getInvcah() {
        return invcah;
    }

    public void setInvcah(Invcah invcah) {
        this.invcah = invcah;
    }

    public Vinvca getVinvca() {
        return vinvca;
    }

    public void setVinvca(Vinvca vinvca) {
        this.vinvca = vinvca;
    }

    public Jourop getJourop() {
        return jourop;
    }

    public void setJourop(Jourop jourop) {
        this.jourop = jourop;
    }

    public double getFluctuation() {
        return fluctuation;
    }

    public void setFluctuation() {
        if(this.vinvca != null){
            this.fluctuation = calculateFluctuation(invcah.getCours(), vinvca.getCours());
        }else if (this.jourop != null){
            this.fluctuation = calculateFluctuation(invcah.getCours(), jourop.getTradePrice());
        }else{
            this.fluctuation = 0;
        }
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

    public void setAlertType() {
        this.alertType = defineAlertType();
    }

    public double calculateFluctuation(double coursJour, double coursVeille) {
        return Math.abs((coursJour-coursVeille)/coursVeille);
    }

    public String defineAlertType() {
        if (fluctuation >= threshold) {
            return "Security's threshold exceeded";
        } else if(!isSameCurrency()) {
            return "Different Currency through each datasource";
        }else{
            return null;
            }
        }

    private boolean isSameCurrency() {
        if(this.jourop == null){
            return invcah.getDeviseCours().equals(vinvca.getDeviseCours());
        }else{
            return invcah.getDeviseCours().equals(jourop.getTradeCurrency());
        }
}


}
