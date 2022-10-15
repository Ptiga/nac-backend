package com.socgen.nac.service.source;

import com.socgen.nac.entity.source.Jourop;
import com.socgen.nac.entity.source.Statement;
import com.socgen.nac.repository.database.StatementRepositoryInterface;
import com.socgen.nac.repository.file.SourceFileRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class JouropServiceTest {

    String sourceFilename = "jourop_kdja_FD0004_20220630_010722105654.fic";
    String codeFonds = "FD0004";
    String categorie = "CAT ";
    String transactionType = "CCT ";
    String isinValeur = "OACT01584483";
    String tradeDate = "20220630";
    double tradePrice =         23538976.39        ;
    String deviseCours = "USD";

    String sourceFolder = "c://temp//GP3_files_test//";
    char dataSeparator = '_';
    int numberOfSeparator = 4;

    SourceFileRepository sourceFileRepository = new SourceFileRepository(sourceFolder, dataSeparator, numberOfSeparator);
    private StatementRepositoryInterface statementRepository;
    StatementService statementService = new StatementService(sourceFileRepository, statementRepository);

    JouropService jouropService = new JouropService();


    @Test
    public void createJourop(){
        Jourop jourop = new Jourop(sourceFilename, codeFonds, categorie, transactionType, isinValeur, tradeDate, tradePrice, deviseCours);
        Assertions.assertEquals(sourceFilename, jourop.getSourceFilename());
        Assertions.assertEquals(codeFonds, jourop.getCodeFonds());
        Assertions.assertEquals(categorie, jourop.getCategorie());
        Assertions.assertEquals(transactionType, jourop.getTransactionType());
        Assertions.assertEquals(isinValeur, jourop.getIsinValeur());
        Assertions.assertEquals(tradeDate, jourop.getTradeDate());
        Assertions.assertEquals(tradePrice, jourop.getTradePrice());
        Assertions.assertEquals(deviseCours, jourop.getTradeCurrency());
    }

    @Test
    public void createJouropFromFiles(){
        List<Statement> listOfFiles = sourceFileRepository.listFiles();
        statementService.manageListOfFunds(listOfFiles);
        statementService.splitToDedicatedList(statementService.getUsableStatementsList());
        List<String[]>extractedList = statementService.createStatementDetail(statementService.getDedicatedList("jourop"));
        List<Jourop> listeDetailJourop = jouropService.createJouropAndAddToList(extractedList);
        Assertions.assertTrue(listeDetailJourop.size()>0);
    }


}
