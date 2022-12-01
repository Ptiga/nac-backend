package com.mabanque.nac.service.source;

import com.mabanque.nac.entity.source.Invcah;

import java.util.List;

public interface InvcahServiceInterface {


    List<Invcah> createInvcahAndAddToList(List<String[]> listDetail);

    void saveInvcahData(List<Invcah> listeInvcah);

    List<Invcah>getUploadedInvcah();
}
