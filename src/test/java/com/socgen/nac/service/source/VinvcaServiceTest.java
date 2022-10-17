package com.socgen.nac.service.source;

import com.socgen.nac.entity.source.Statement;
import com.socgen.nac.entity.source.Vinvca;
import com.socgen.nac.repository.database.InvcahRepositoryInterface;
import com.socgen.nac.repository.database.JouropRepositoryInterface;
import com.socgen.nac.repository.database.StatementRepositoryInterface;
import com.socgen.nac.repository.database.VinvcaRepositoryInterface;
import com.socgen.nac.repository.file.SourceFileRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class VinvcaServiceTest{

    String filename = "vinvca_kdja_FD0004_20220629_300622091342.fic";
    String fonds = "FD0004";
    String nomFonds = "FUND_NUM_04";
    String deviseFonds = "EUR";
    String dataValo = "20220630";
    String triComptable = "0";
    String categorie = "VMOB";
    String isin = "FR0000120321";
    String libelleValeur = "LOREAL SA";
    String dateCours = "20220629";
    double prix = 326.0;
    String devise = "EUR";

    String sourceFolder = "c://temp//GP3_files_test//";
    char dataSeparator = '_';
    int numberOfSeparator = 4;
    SourceFileRepository sourceFileRepository = new SourceFileRepository(sourceFolder, dataSeparator, numberOfSeparator);
    private StatementRepositoryInterface statementRepository;
    StatementService statementService = new StatementService(sourceFileRepository, statementRepository);

    VinvcaRepositoryInterface vinvcaRepository;

    VinvcaService vinvcaService = new VinvcaService(vinvcaRepository);

    List<Statement>uploadedStatements = new ArrayList<>();

    @Test
    public void testVinvcaConstructor(){
        Vinvca vinvca = new Vinvca(filename, fonds, nomFonds, deviseFonds, dataValo, triComptable, categorie, isin, libelleValeur, dateCours, prix, devise);
        Assertions.assertEquals(filename, vinvca.getSourceFilename());
        Assertions.assertEquals(fonds, vinvca.getCodeFonds());
        Assertions.assertEquals(nomFonds, vinvca.getNomfonds());
        Assertions.assertEquals(deviseFonds, vinvca.getDeviseFonds());
        Assertions.assertEquals(dataValo, vinvca.getDateVL());
        Assertions.assertEquals(triComptable, vinvca.getTriComptable());
        Assertions.assertEquals(categorie, vinvca.getCategorie());
        Assertions.assertEquals(isin, vinvca.getIsinValeur());
        Assertions.assertEquals(libelleValeur, vinvca.getLibeleValeur());
        Assertions.assertEquals(dateCours, vinvca.getDateCours());
        Assertions.assertEquals(prix, vinvca.getCours());
        Assertions.assertEquals(devise, vinvca.getDeviseCours());
    }

    @Test
    public void createVinvcaFromFiles(){
        List<Statement> listOfFiles = sourceFileRepository.listFiles();
        statementService.manageListOfFunds(listOfFiles, uploadedStatements);
        statementService.splitToDedicatedList(statementService.getUsableStatementsList());
        List<String[]>extractedList = statementService.createStatementDetail(statementService.getDedicatedList("vinvca"));
        List<Vinvca> listeDetailVinvca = vinvcaService.createVinvcaAndAddToList(extractedList);
        Assertions.assertTrue(listeDetailVinvca.size()>0);
    }

    @Test
    public void getVinvcaList(){
        List<Statement> listOfFiles = sourceFileRepository.listFiles();
        statementService.manageListOfFunds(listOfFiles, uploadedStatements);
        statementService.splitToDedicatedList(statementService.getUsableStatementsList());
        List<String[]>extractedList = statementService.createStatementDetail(statementService.getDedicatedList("vinvca"));
        List<Vinvca> listeDetailVinvca = vinvcaService.createVinvcaAndAddToList(extractedList);
        Assertions.assertTrue(listeDetailVinvca.size()>0);
    }

}
