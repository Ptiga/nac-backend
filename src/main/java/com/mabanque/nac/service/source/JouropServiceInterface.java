package com.mabanque.nac.service.source;

import com.mabanque.nac.entity.source.Jourop;

import java.util.List;

public interface JouropServiceInterface {

    List<Jourop> createJouropAndAddToList(List<String[]> listDetail);

    void saveJouropData(List<Jourop>listeJourpo);

    List<Jourop>getUploadedJourop();
}
