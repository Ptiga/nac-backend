package com.mabanque.nac.service.source;

import com.mabanque.nac.entity.source.Vinvca;

import java.util.List;

public interface VinvcaServiceInterface {

    List<Vinvca> createVinvcaAndAddToList(List<String[]> listDetail);

    void saveVinvcaData(List<Vinvca>listeVinvca);

    List<Vinvca>getUploadedVinvca();
}
