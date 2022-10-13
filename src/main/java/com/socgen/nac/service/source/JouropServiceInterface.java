package com.socgen.nac.service.source;

import com.socgen.nac.entity.source.Jourop;

import java.util.List;

public interface JouropServiceInterface {

    List<Jourop> createJouropAndAddToList(List<String[]> listDetail);

}
