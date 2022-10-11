package com.socgen.nac.service.source;

import com.socgen.nac.entity.source.Invcah;
import com.socgen.nac.entity.source.Statement;

import java.util.List;

public interface InvcahServiceInterface {

    void createInvcahFromList(List<String[]> listDetail);
}
