package com.socgen.nac.service.check;

import com.socgen.nac.entity.check.CheckFluctuationData;
import com.socgen.nac.entity.source.Invcah;
import com.socgen.nac.entity.source.Jourop;
import com.socgen.nac.entity.source.Vinvca;
import com.socgen.nac.repository.file.SourceFileRepositoryInterface;
import com.socgen.nac.service.source.InvcahServiceInterface;
import com.socgen.nac.service.source.JouropServiceInterface;
import com.socgen.nac.service.source.VinvcaService;
import com.socgen.nac.service.source.VinvcaServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CheckFluctuationService implements CheckFluctuationServiceInterface{

    private List<CheckFluctuationData>listeCheckFluctuation = new ArrayList<>();

    public List<CheckFluctuationData> getListeCheckFluctuation() {
        return listeCheckFluctuation;
    }


    @Autowired
    private InvcahServiceInterface invcahService;

    public InvcahServiceInterface getInvcahService() {
        return invcahService;
    }

    public void setInvcahService(InvcahServiceInterface invcahService) {
        this.invcahService = invcahService;
    }

    @Autowired
    private VinvcaServiceInterface vinvcaService;

    public VinvcaServiceInterface getVinvcaService() {
        return vinvcaService;
    }

    public void setVinvcaService(VinvcaServiceInterface vinvcaService) {
        this.vinvcaService = vinvcaService;
    }

    @Autowired
    private JouropServiceInterface jouropService;

    public JouropServiceInterface getJouropService() {
        return jouropService;
    }

    public void setJouropService(JouropServiceInterface jouropService) {
        this.jouropService = jouropService;
    }

/*
    public CheckFluctuationService(List<CheckFluctuationData>listeCheckFluctuation, InvcahServiceInterface invcahService, VinvcaServiceInterface vinvcaService, JouropServiceInterface jouropService){
        this.listeCheckFluctuation = listeCheckFluctuation;
        this.invcahService = invcahService;
        this.vinvcaService = vinvcaService;
        this.jouropService = jouropService;
    }
*/
    public CheckFluctuationService(InvcahServiceInterface invcahService, VinvcaServiceInterface vinvcaService, JouropServiceInterface jouropService){
        this.invcahService = invcahService;
        this.vinvcaService = vinvcaService;
        this.jouropService = jouropService;
    }


    @Override
    public double calculateFluctuation(double coursJour, double coursVeille) {
        return Math.abs((coursJour-coursVeille)/coursVeille);
    }

    @Override
    public double retrieveTheshold(Map thresholdList, String triComptable) {
        return (double) thresholdList.get(triComptable);
    }

    @Override
    public boolean compareInvcahAndVinvca(Invcah invcah, Vinvca vinvca) {
        if(invcah.getCodeFonds().equals(vinvca.getCodeFonds()) &&
                invcah.getTriComptable().equals(vinvca.getTriComptable()) &&
                invcah.getCategorie().equals(vinvca.getCategorie()) &&
                invcah.getIsinValeur().equals(vinvca.getIsinValeur())) {
            return true;
        }else{
            return false;
            }
        }

    @Override
    public boolean compareInvcahAndJourop(Invcah invcah, Jourop jourop) {
        if(invcah.getCodeFonds().equals(jourop.getCodeFonds()) &&
                invcah.getCategorie().equals(jourop.getCategorie()) &&
                invcah.getIsinValeur().equals(jourop.getIsinValeur())) {
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean isInvcahUsableForCheck(Invcah invcah) {
        if(isCategoryCorrect(invcah.getCategorie()) && isTriComptableCorrect(invcah.getTriComptable())){
            return true;
        }else{
            return false;
        }
    }



    @Override
    //Créer méthode void ?  -> Ajouter directement dans une liste si créée ?
    public void createCheckFluctuationData(Invcah invcah) {

        for (Vinvca vinvca: vinvcaService.getListeDetailVinvca()) {
            if(compareInvcahAndVinvca(invcah, vinvca)){
                listeCheckFluctuation.add(new CheckFluctuationData(invcah, vinvca));
            }
            else{
                for (Jourop jourop : jouropService.getListeDetailJourop()) {
                    if (compareInvcahAndJourop(invcah, jourop)) {
                        listeCheckFluctuation.add(new CheckFluctuationData(invcah, jourop));
                    }
                }
            }

            }
        }


    public boolean isCategoryCorrect(String categorie) {
        if (categorie.equals("VMOB") || categorie.equals("FUTU") || categorie.equals("OPTI")){
            return true;
        }else{
            return false;
        }
    }

    public boolean isTriComptableCorrect(String triComptable) {
        switch (triComptable){
            case "0", "1", "2", "3", "4", "5", "6":
                return true;
            default:
                return false;
        }
    }
}
