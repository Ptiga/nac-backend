package com.mabanque.nac.service.source;

import com.mabanque.nac.entity.source.Vinvca;
import com.mabanque.nac.repository.database.VinvcaRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class VinvcaService implements VinvcaServiceInterface {

    @Autowired
    VinvcaRepositoryInterface vinvcaRepository;


    public VinvcaRepositoryInterface getVinvcaRepository() {
        return vinvcaRepository;
    }

    public void setVinvcaRepository(VinvcaRepositoryInterface vinvcaRepository) {
        this.vinvcaRepository = vinvcaRepository;
    }


    public VinvcaService(VinvcaRepositoryInterface vinvcaRepository){
        this.vinvcaRepository = vinvcaRepository;
    }


    @Override
    public List<Vinvca> createVinvcaAndAddToList(List<String[]> listDetail) {
        List<Vinvca> listeDetailVinvca = new ArrayList<>();
        for (String[] valeur: listDetail) {
            if(valeur[0].substring(0,6).equals("vinvca")) {
                listeDetailVinvca.add(new Vinvca(valeur[0], valeur[1], valeur[2],valeur[21], valeur[13], valeur[7],valeur[14], valeur[17], valeur[18],valeur[65], Double.parseDouble(valeur[24]), valeur[47]));
            }
        }
        return listeDetailVinvca;
    }

    @Override
    public void saveVinvcaData(List<Vinvca> listeVinvca) {
        vinvcaRepository.saveAll(listeVinvca);
    }

    @Override
    public List<Vinvca> getUploadedVinvca() {
        List<Vinvca>uploadedVinvca = new ArrayList<>();
        Iterable<Vinvca> vinvcaFromDatabase = vinvcaRepository.findAll();
        vinvcaFromDatabase.forEach(uploadedVinvca::add);
        return uploadedVinvca;
    }

}
