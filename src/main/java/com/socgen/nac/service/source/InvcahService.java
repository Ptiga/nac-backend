package com.socgen.nac.service.source;

import com.socgen.nac.entity.source.Invcah;
import com.socgen.nac.entity.source.Statement;
import com.socgen.nac.repository.database.InvcahRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InvcahService implements InvcahServiceInterface {

    @Autowired
    InvcahRepositoryInterface invcahRepository;

    public InvcahRepositoryInterface getInvcahRepository() {
        return invcahRepository;
    }

    public void setInvcahRepository(InvcahRepositoryInterface invcahRepository) {
        this.invcahRepository = invcahRepository;
    }


    public InvcahService(InvcahRepositoryInterface invcahRepository){
        this.invcahRepository = invcahRepository;
    }


    @Override
    public List<Invcah> createInvcahAndAddToList(List<String[]> listDetail) {
        List<Invcah> listeDetailInvcah = new ArrayList<>();
        for (String[] valeur: listDetail) {
            if(valeur[0].substring(0,6).equals("invcah")) {
                listeDetailInvcah.add(new Invcah(valeur[0], valeur[1], valeur[2], valeur[21], valeur[13], valeur[7], valeur[14], valeur[17], valeur[18], valeur[65], Double.parseDouble(valeur[24]), valeur[47]));
            }
        }
        return listeDetailInvcah;
    }

    @Override
    public void saveInvcahData(List<Invcah> listeInvcah) {
        invcahRepository.saveAll(listeInvcah);
    }

    @Override
    public List<Invcah> getUploadedInvcah() {
        List<Invcah>uploadedInvcah = new ArrayList<>();
        Iterable<Invcah> invcahFromDatabase = invcahRepository.findAll();
        invcahFromDatabase.forEach(uploadedInvcah::add);
        return uploadedInvcah;
    }
}
