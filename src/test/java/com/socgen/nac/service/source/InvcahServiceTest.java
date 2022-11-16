package com.socgen.nac.service.source;

import com.socgen.nac.entity.source.Invcah;
import com.socgen.nac.entity.source.Statement;
import com.socgen.nac.repository.database.InvcahRepositoryInterface;
import com.socgen.nac.repository.database.JouropRepositoryInterface;
import com.socgen.nac.repository.database.StatementRepositoryInterface;
import com.socgen.nac.repository.database.VinvcaRepositoryInterface;
import com.socgen.nac.repository.file.SourceFileRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class InvcahServiceTest {

    String filename = "invcah_kdja_FD0004_20220630_010722105654";
    String fonds = "FD0004";
    String nomFonds = "FUND_NUM_04";
    String deviseFonds = "EUR";
    String dataValo = "20220630";
    String triComptable = "0";
    String categorie = "VMOB";
    String isin = "FR0000120321";
    String libelleValeur = "LOREAL SA";
    String dateCours = "20220630";
    double prix = 322.1;
    String deviseCours = "EUR";

    String sourceFolder = "c://temp//GP3_files_test//";
    char dataSeparator = '_';
    int numberOfSeparator = 4;

    InvcahRepositoryInterface invcahRepository;
    VinvcaRepositoryInterface vinvcaRepository;
    JouropRepositoryInterface jouropRepository;

    InvcahService invcahService = new InvcahService(invcahRepository);
    VinvcaService vinvcaService = new VinvcaService(vinvcaRepository);
    JouropService jouropService = new JouropService(jouropRepository);

    SourceFileRepository sourceFileRepository = new SourceFileRepository(sourceFolder, dataSeparator, numberOfSeparator);
    private StatementRepositoryInterface statementRepository;
    StatementService statementService = new StatementService(sourceFileRepository, invcahService, vinvcaService, jouropService, statementRepository);



    List<Statement>uploadedStatements = new ArrayList<>();


    @Test
    public void testInvcahConstructor(){
        Invcah invcah = new Invcah(filename, fonds, nomFonds, deviseFonds, dataValo, triComptable, categorie, isin, libelleValeur, dateCours, prix, deviseCours);
        Assertions.assertEquals(filename, invcah.getSourceFilename());
        Assertions.assertEquals(fonds, invcah.getCodeFonds());
        Assertions.assertEquals(nomFonds, invcah.getNomfonds());
        Assertions.assertEquals(deviseFonds, invcah.getDeviseFonds());
        Assertions.assertEquals(dataValo, invcah.getDateVL());
        Assertions.assertEquals(triComptable, invcah.getTriComptable());
        Assertions.assertEquals(categorie, invcah.getCategorie());
        Assertions.assertEquals(isin, invcah.getIsinValeur());
        Assertions.assertEquals(libelleValeur, invcah.getLibeleValeur());
        Assertions.assertEquals(dateCours, invcah.getDateCours());
        Assertions.assertEquals(prix, invcah.getCours());
        Assertions.assertEquals(deviseCours, invcah.getDeviseCours());
    }


    @Test
    public void createInvcahFromFiles(){
        List<Statement> listOfFiles = sourceFileRepository.listFiles();
        statementService.manageListOfFunds(listOfFiles, uploadedStatements);
        statementService.splitToDedicatedList(statementService.getUsableStatementsList());
        List<String[]>extractedList = statementService.createStatementDetail(statementService.getDedicatedList("invcah"));
        List<Invcah> listeDetailInvcah = invcahService.createInvcahAndAddToList(extractedList);
        Assertions.assertTrue(listeDetailInvcah.size()>0);
    }


}
