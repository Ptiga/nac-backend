package com.mabanque.nac.service.check;

import com.mabanque.nac.service.source.InvcahServiceInterface;
import com.mabanque.nac.service.source.JouropServiceInterface;
import com.mabanque.nac.service.source.VinvcaServiceInterface;
import com.mabanque.nac.entity.check.CheckFluctuationData;
import com.mabanque.nac.entity.source.Invcah;
import com.mabanque.nac.entity.source.Jourop;
import com.mabanque.nac.entity.source.Threshold;
import com.mabanque.nac.entity.source.Vinvca;
import com.mabanque.nac.repository.database.ThresholdRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    @Autowired
    private ThresholdRepositoryInterface thresholdRepository;

    public ThresholdRepositoryInterface getThresholdRepository() {
        return thresholdRepository;
    }

    public void setThresholdRepository(ThresholdRepositoryInterface thresholdRepository) {
        this.thresholdRepository = thresholdRepository;
    }

    public CheckFluctuationService(InvcahServiceInterface invcahService, VinvcaServiceInterface vinvcaService, JouropServiceInterface jouropService, ThresholdRepositoryInterface thresholdRepository){
        this.invcahService = invcahService;
        this.vinvcaService = vinvcaService;
        this.jouropService = jouropService;
        this.thresholdRepository = thresholdRepository;
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

    public List<CheckFluctuationData> createCheckDataFromInvcahList(List<Invcah>listInvcah, List<Vinvca>listVinvca, List<Jourop>listJourop){
        List<CheckFluctuationData>listeCheckFluctuation = new ArrayList<>();
        List<Threshold> thresholds = createThresholds();
        for (Invcah invcah: listInvcah) {
            if(isInvcahUsableForCheck(invcah)){
                listeCheckFluctuation.add(createCheckFluctuationData(invcah, listVinvca, listJourop, thresholds));
            }
        }
        return listeCheckFluctuation;
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
    public CheckFluctuationData createCheckFluctuationData(Invcah invcah, List<Vinvca>listVinvca, List<Jourop>listJourop, List<Threshold> thresholds) {
        //List<Threshold> thresholds = createThresholds();
        for (Vinvca vinvca: listVinvca) {
            if(compareInvcahAndVinvca(invcah, vinvca)){
                return new CheckFluctuationData(invcah, vinvca, searchThresholdByTriComptable(thresholds, invcah.getTriComptable()));

            }
            else{
                for (Jourop jourop : listJourop) {
                    if (compareInvcahAndJourop(invcah, jourop)) {
                        return new CheckFluctuationData(invcah, jourop, searchThresholdByTriComptable(thresholds, invcah.getTriComptable()));
                    }
                }
            }

            }
        return null;
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
            case "0":
            case "1":
            case "2":
            case "3":
            case "4":
            case "5":
            case "6":
                return true;
            default:
                return false;
        }
    }

    public Threshold searchThresholdByTriComptable(List<Threshold> thresholds, String triComptable){
        for (Threshold threshold: thresholds) {
            if (threshold.getTriComptable().equals(triComptable)){
                return threshold;
            }
        }
        return null;
    }

    public List<Threshold> createThresholds(){
        List<Threshold>thresholds = new ArrayList<>();
        Iterable<Threshold> thresholdsFromDatabase = thresholdRepository.findAll();
        thresholdsFromDatabase.forEach(thresholds::add);
        return thresholds;
    }

}
