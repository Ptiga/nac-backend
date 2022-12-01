package com.mabanque.nac.service.source;

import com.mabanque.nac.entity.source.Jourop;
import com.mabanque.nac.repository.database.JouropRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class JouropService implements JouropServiceInterface{

    @Autowired
    JouropRepositoryInterface jouropRepository;


    public JouropRepositoryInterface getJouropRepository() {
        return jouropRepository;
    }

    public void setJouropRepository(JouropRepositoryInterface jouropRepository) {
        this.jouropRepository = jouropRepository;
    }


    public JouropService(JouropRepositoryInterface jouropRepository){
        this.jouropRepository = jouropRepository;
    }


    @Override
    public List<Jourop> createJouropAndAddToList(List<String[]> listDetail) {
        List<Jourop> listeDetailJourop = new ArrayList<>();
        for (String[] valeur: listDetail) {
            if(valeur[0].substring(0,6).equals("jourop")) {
                listeDetailJourop.add(new Jourop(valeur[4], valeur[0], valeur[1], valeur[2],valeur[6], valeur[3], valeur[9],Double.parseDouble(valeur[14]), valeur[15]));
            }
        }
        return listeDetailJourop;
    }

    @Override
    public void saveJouropData(List<Jourop> listeJourop) {
        jouropRepository.saveAll(listeJourop);
    }

    @Override
    public List<Jourop> getUploadedJourop() {
        List<Jourop>uploadedJourop = new ArrayList<>();
        Iterable<Jourop> jouropFromDatabase = jouropRepository.findAll();
        jouropFromDatabase.forEach(uploadedJourop::add);
        return uploadedJourop;
    }
}
