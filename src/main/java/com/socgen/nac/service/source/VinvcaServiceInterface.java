package com.socgen.nac.service.source;

import com.socgen.nac.entity.source.Vinvca;

import java.util.List;

public interface VinvcaServiceInterface {

    List<Vinvca> getListeDetailVinvca();

    void addVinvcaToList(Vinvca vinvca);

    void createVinvcaFromList(List<String[]> listDetail);
}
