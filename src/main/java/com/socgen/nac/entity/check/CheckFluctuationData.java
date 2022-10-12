package com.socgen.nac.entity.check;

import com.socgen.nac.entity.source.Invcah;
import com.socgen.nac.entity.source.Jourop;
import com.socgen.nac.entity.source.Vinvca;

public class CheckFluctuationData {

    private Invcah invcah;
    private Vinvca vinvca;
    private Jourop jourop;


    public CheckFluctuationData(Invcah invcah, Vinvca vinvca) {
        this.invcah = invcah;
        this.vinvca = vinvca;
    }

    public CheckFluctuationData(Invcah invcah, Jourop jourop) {
        this.invcah = invcah;
        this.jourop = jourop;
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
}
