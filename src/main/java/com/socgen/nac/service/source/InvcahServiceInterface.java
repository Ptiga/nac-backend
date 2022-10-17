package com.socgen.nac.service.source;

import com.socgen.nac.entity.source.Invcah;
import com.socgen.nac.entity.source.Statement;

import java.util.ArrayList;
import java.util.List;

public interface InvcahServiceInterface {


    List<Invcah> createInvcahAndAddToList(List<String[]> listDetail);

    void saveInvcahData(List<Invcah> listeInvcah);

    List<Invcah>getUploadedInvcah();
}
