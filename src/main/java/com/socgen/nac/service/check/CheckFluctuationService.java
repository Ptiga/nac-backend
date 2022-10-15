package com.socgen.nac.service.check;

import com.socgen.nac.entity.check.CheckFluctuationData;
import com.socgen.nac.entity.source.Invcah;
import com.socgen.nac.entity.source.Jourop;
import com.socgen.nac.entity.source.Threshold;
import com.socgen.nac.entity.source.Vinvca;
import com.socgen.nac.repository.database.ThresholdRepositoryInterface;
import com.socgen.nac.service.source.InvcahServiceInterface;
import com.socgen.nac.service.source.JouropServiceInterface;
import com.socgen.nac.service.source.VinvcaServiceInterface;
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

    //private Map<String, Double> thresholds = fillThresholds();
/*
    @Override
    public Map<String, Double> getThresholds() {
        return thresholds;
    }

 */
/*
    private Map<String, Double> fillThresholds(){
        Map<String, Double> thresholdsMap = new HashMap<>();
        thresholdsMap.put("0", 0.05);
        thresholdsMap.put("1", 0.015);
        thresholdsMap.put("2", 0.005);
        thresholdsMap.put("3", 0.02);
        thresholdsMap.put("4", 0.03);
        thresholdsMap.put("5", 0.3);
        thresholdsMap.put("6", 0.1);
        thresholdsMap.put("7", 1.0);
        thresholdsMap.put("T", 1.0);
        return thresholdsMap;
    }
*/
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

/*
    @Override
    public double calculateFluctuation(double coursJour, double coursVeille) {
        return Math.abs((coursJour-coursVeille)/coursVeille);
    }
*/
    /*
    @Override
    public double retrieveTheshold(Map thresholdList, String triComptable) {
        return (double) thresholdList.get(triComptable);
    }
*/
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
                //listeCheckFluctuation.add(new CheckFluctuationData(invcah, vinvca, thresholds.get(invcah.getTriComptable())));
                //return new CheckFluctuationData(invcah, vinvca, thresholds.get(invcah.getTriComptable()));
                return new CheckFluctuationData(invcah, vinvca, searchThresholdByTriComptable(thresholds, invcah.getTriComptable()));
                //break;
            }
            else{
                for (Jourop jourop : listJourop) {
                    if (compareInvcahAndJourop(invcah, jourop)) {
                        //listeCheckFluctuation.add(new CheckFluctuationData(invcah, jourop, thresholds.get(invcah.getTriComptable())));
                        //return new CheckFluctuationData(invcah, jourop, thresholds.get(invcah.getTriComptable()));
                        return new CheckFluctuationData(invcah, jourop, searchThresholdByTriComptable(thresholds, invcah.getTriComptable()));
                        //break;
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
            case "0", "1", "2", "3", "4", "5", "6":
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
        /*
        thresholds.add(new Threshold("0", "Securities",0.05));
        thresholds.add(new Threshold("1", "Bonds",0.015));
        thresholds.add(new Threshold("2", "Debt securities",0.005));
        thresholds.add(new Threshold("3", "Ucits - ETF",0.02));
        thresholds.add(new Threshold("4", "Ucits",0.02));
        thresholds.add(new Threshold("5", "Futures",0.03));
        thresholds.add(new Threshold("6", "Options",0.3));
        thresholds.add(new Threshold("7", "Swap",1.0));
        thresholds.add(new Threshold("T", "X-currencies", 1.0));
        return thresholds;
         */
        Iterable<Threshold> thresholdsFromDatabase = thresholdRepository.findAll();
        thresholdsFromDatabase.forEach(thresholds::add);
        return thresholds;
    }

}
