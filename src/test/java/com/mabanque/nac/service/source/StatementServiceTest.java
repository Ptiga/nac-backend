package com.mabanque.nac.service.source;

import com.mabanque.nac.repository.database.InvcahRepositoryInterface;
import com.mabanque.nac.repository.database.JouropRepositoryInterface;
import com.mabanque.nac.repository.database.StatementRepositoryInterface;
import com.mabanque.nac.repository.database.VinvcaRepositoryInterface;
import com.mabanque.nac.repository.file.SourceFileRepository;
import com.mabanque.nac.entity.source.Statement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class StatementServiceTest {

    String filename = "jourop_kdja_FD0004_20220629_300622091342.fic";
    String filename2 = "vinvca_kdja_FD0004_20220630_010722105654.fic";
    String filename3 = "invcah_kdja_FD0004_20220630_010722105654.fic";
    String sourceFolder = "c://temp//GP3_files_test_class//";
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
    public void testStatementConstructor(){
        Statement statement = new Statement(filename, dataSeparator, numberOfSeparator);
        Assertions.assertEquals(filename, statement.getFilename());
        Assertions.assertEquals("jourop", statement.getStatementType());
        Assertions.assertEquals("fic", statement.getFileExtension());
    }

    @Test
    public void testIsStatementCorrect(){
        Statement statement = new Statement(filename, dataSeparator, numberOfSeparator);
        Assertions.assertTrue(statementService.isExpectedStatementType(statement));
    }

    @Test
    public void testIsStatementIncorrect(){
        Statement statement = new Statement("autres_kdja_FD0004_20220629_300622091342.fic", dataSeparator, numberOfSeparator);
        Assertions.assertFalse(statementService.isExpectedStatementType(statement));
    }

    @Test
    void testIsFormatCorrect(){
        Statement statement = new Statement(filename, dataSeparator, numberOfSeparator);
        Assertions.assertTrue(statementService.isExpectedFormat(statement));
    }

    @Test
    void testIsFormatIncorrect(){
        Statement statement = new Statement("jourop_kdja_FD0004_20220629_300622091342.txt", dataSeparator, numberOfSeparator);
        Assertions.assertFalse(statementService.isExpectedFormat(statement));
    }

    @Test
    void testStatementUsableness(){
        Statement statement = new Statement(filename, dataSeparator, numberOfSeparator);
        Assertions.assertTrue(statementService.isStatementUsable(statement));
    }

    @Test
    void testStatementNotUsable(){
        Statement statement = new Statement("invcah_je_ne_suis_pas_un_etat_utilisable.fic", dataSeparator, numberOfSeparator);
        Assertions.assertFalse(statementService.isStatementUsable(statement));
    }

    @Test
    void testAddingRemainingAttributes(){
        Statement statement = new Statement(filename, dataSeparator, numberOfSeparator);
        statementService.addRemainingAttributes(statement);
        Assertions.assertEquals(filename, statement.getFilename());
        Assertions.assertEquals("jourop", statement.getStatementType());
        Assertions.assertEquals("kdja", statement.getUserTag());
        Assertions.assertEquals("FD0004", statement.getFund());
        Assertions.assertEquals("20220629", statement.getNavDate());
        Assertions.assertEquals("300622091342", statement.getFileTimestamp());
        Assertions.assertEquals("fic", statement.getFileExtension());
        Assertions.assertEquals(4, statement.getNumberOfSeparators());
    }

    @Test
    void manageCheckSourceFolder(){
        statementService.checkSourceFolder();
        Assertions.assertEquals(30, sourceFileRepository.listFiles().size());
    }

    @Test
    void manageListOfFiles(){
        List<Statement> listOfFiles = sourceFileRepository.listFiles();
        statementService.manageListOfFunds(listOfFiles, uploadedStatements);
        Assertions.assertEquals(18, statementService.getUsableStatementsList().size());
    }

    @Test
    void splitToDedicatedList(){
        List<Statement> listOfFiles = sourceFileRepository.listFiles();
        statementService.manageListOfFunds(listOfFiles, uploadedStatements);
        statementService.splitToDedicatedList(statementService.getUsableStatementsList());
        Assertions.assertEquals(6, statementService.getDedicatedList("invcah").size());
        Assertions.assertEquals(6, statementService.getDedicatedList("vinvca").size());
        Assertions.assertEquals(6, statementService.getDedicatedList("jourop").size());
    }


    @Test
    void putJouropInTheCorrectList(){
        statementService.putStatementInProperList(new Statement(filename, dataSeparator, numberOfSeparator));
        Assertions.assertEquals(1, statementService.getDedicatedList("jourop").size());
    }

    @Test
    void getDedicatedList(){
        statementService.putStatementInProperList(new Statement(filename, dataSeparator, numberOfSeparator));
        Assertions.assertEquals(0, statementService.getDedicatedList("invcah").size());
        Assertions.assertEquals(0, statementService.getDedicatedList("vinvca").size());
        Assertions.assertEquals(1, statementService.getDedicatedList("jourop").size());
    }

    @Test
    void isStatementContainsHeader(){
        Assertions.assertTrue(new Statement(filename3, dataSeparator, numberOfSeparator).isStatementContainsHeader());
        Assertions.assertTrue(new Statement(filename2, dataSeparator, numberOfSeparator).isStatementContainsHeader());
        Assertions.assertFalse(new Statement(filename, dataSeparator, numberOfSeparator).isStatementContainsHeader());
        Assertions.assertFalse(new Statement(filename, dataSeparator, numberOfSeparator).isStatementContainsHeader());
    }

    @Test
    void createDetail(){
        List<Statement> listOfFiles = sourceFileRepository.listFiles();
        statementService.manageListOfFunds(listOfFiles, uploadedStatements);
        statementService.splitToDedicatedList(statementService.getUsableStatementsList());
        List<String[]>extractedList = statementService.createStatementDetail(statementService.getDedicatedList("invcah"));
        System.out.println(extractedList);
        Assertions.assertTrue(extractedList.size()>0);
    }

    @Test
    void doNotkeepIfAlreadyInDatabase(){
        List<Statement> listOfFiles = new ArrayList<>();
        listOfFiles.add(new Statement(filename, dataSeparator, numberOfSeparator));
        uploadedStatements.add(new Statement(filename, dataSeparator, numberOfSeparator));
        statementService.manageListOfFunds(listOfFiles, uploadedStatements);
        Assertions.assertTrue(statementService.getUsableStatementsList().size()==0);
    }

    @Test
    void keepOnlyNewElements(){
        List<Statement> listOfFiles = new ArrayList<>();
        listOfFiles.add(new Statement(filename, dataSeparator, numberOfSeparator));
        listOfFiles.add(new Statement(filename2, dataSeparator, numberOfSeparator));
        uploadedStatements.add(new Statement(filename, dataSeparator, numberOfSeparator));
        statementService.manageListOfFunds(listOfFiles, uploadedStatements);
        Assertions.assertTrue(statementService.getUsableStatementsList().size()==1);
    }

}
