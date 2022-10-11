package com.socgen.nac.service.check;

import com.socgen.nac.entity.check.CheckFluctuationData;
import com.socgen.nac.entity.source.Invcah;
import com.socgen.nac.entity.source.Jourop;
import com.socgen.nac.entity.source.Vinvca;
import com.socgen.nac.repository.file.SourceFileRepositoryInterface;
import com.socgen.nac.service.source.InvcahServiceInterface;
import com.socgen.nac.service.source.JouropServiceInterface;
import com.socgen.nac.service.source.VinvcaServiceInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class CheckFluctuationService implements CheckFluctuationServiceInterface{

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

    public void setInvcahService(VinvcaServiceInterface vinvcaService) {
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




    @Override
    public double calculateFluctuation(Vinvca vinvca, Invcah invcah) {
        return Math.abs((invcah.getCours()-vinvca.getCours())/vinvca.getCours());
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
    //Créer méthode void ?  -> Ajouter directement dans une liste si créée ?
    public Object createCheckFluctuationData(Invcah invcah) {

        //Compare avec listeVinvca

        //Compare avec liste Jourop

        //Rejet




        return null;
    }
}
