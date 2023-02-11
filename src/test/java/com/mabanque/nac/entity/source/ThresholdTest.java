package com.mabanque.nac.entity.source;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class ThresholdTest {

    @Test
    public void createThresholdList(){
        List<Threshold> listeThreshold = new ArrayList<>();
        listeThreshold.add(new Threshold("0", "Securities",0.05));
        listeThreshold.add(new Threshold("1", "Bonds",0.015));
        listeThreshold.add(new Threshold("2", "Debt securities",0.005));
        listeThreshold.add(new Threshold("4", "Ucits",0.02));
        listeThreshold.add(new Threshold("5", "Futures",0.03));
        listeThreshold.add(new Threshold("6", "Options",0.3));
        listeThreshold.add(new Threshold("7", "Swap",1.0));
        listeThreshold.add(new Threshold("T", "X-currencies", 1.0));

        Assertions.assertEquals(8, listeThreshold.size());
    }

    @Test
    public void SearchInsideThresholdList(){
        List<Threshold> listeThreshold = new ArrayList<>();
        listeThreshold.add(new Threshold("0", "Securities",0.05));
        listeThreshold.add(new Threshold("1", "Bonds",0.015));
        listeThreshold.add(new Threshold("2", "Debt securities",0.005));
        listeThreshold.add(new Threshold("4", "Ucits",0.02));
        listeThreshold.add(new Threshold("5", "Futures",0.03));
        listeThreshold.add(new Threshold("6", "Options",0.3));
        listeThreshold.add(new Threshold("7", "Swap",1.0));
        listeThreshold.add(new Threshold("T", "X-currencies", 1.0));

        String triComptable = "5";

        String instrument = "";
        double threshold = 0.0;

        for (Threshold t: listeThreshold) {
            if (t.getTriComptable().equals(triComptable)){
                instrument = t.getTypeInstrument();
                threshold = t.getThreshold();
            }
        }
        Assertions.assertEquals("Futures", instrument);
        Assertions.assertEquals(0.03, threshold);
    }

}
