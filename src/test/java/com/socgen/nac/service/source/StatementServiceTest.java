package com.socgen.nac.service.source;

import com.socgen.nac.entity.source.Statement;
import com.socgen.nac.service.source.StatementService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

public class StatementServiceTest {

    String filename = "jourop_kdja_FD0004_20220629_300622091342.fic";
    String filename2 = "vinvca_kdja_FD0004_20220630_010722105654.fic";
    String filename3 = "invcah_kdja_FD0004_20220630_010722105654.fic";
    String filename4 = "vinvca_kdja_FD0004_20220629_300622091342.fic";
    String filename5 = "jourop_kdja_FD0004_20220629_300622091342.fic";
    String filename6 = "invcah_kdja_FD0004_20220629_300622091342.fic";

    StatementService statementService = new StatementService();

    @Test
    public void testStatementConstructor(){
        Statement statement = statementService.createStatement(filename);
        Assertions.assertEquals(filename, statement.getFilename());
        Assertions.assertEquals("jourop", statement.getStatementType());
        Assertions.assertEquals("fic", statement.getFileExtension());
    }

    @Test
    public void testIsStatementCorrect(){
        Statement statement = statementService.createStatement(filename);
        Assertions.assertTrue(statementService.isExpectedStatementType(statement));
    }

    @Test
    public void testIsStatementIncorrect(){
        Statement statement = statementService.createStatement("autres_kdja_FD0004_20220629_300622091342.fic");
        Assertions.assertFalse(statementService.isExpectedStatementType(statement));
    }

    @Test
    void testIsFormatCorrect(){
        Statement statement = statementService.createStatement(filename);
        Assertions.assertTrue(statementService.isExpectedFormat(statement));
    }

    @Test
    void testIsFormatIncorrect(){
        Statement statement = statementService.createStatement("jourop_kdja_FD0004_20220629_300622091342.txt");
        Assertions.assertFalse(statementService.isExpectedFormat(statement));
    }

    @Test
    void testStatementUsableness(){
        Statement statement = statementService.createStatement(filename);
        Assertions.assertTrue(statementService.isStatementUsable(statement));
    }

    @Test
    void testStatementNotUsable(){
        Statement statement = statementService.createStatement("invcah_je_ne_suis_pas_un_etat_utilisable.fic");
        Assertions.assertFalse(statementService.isStatementUsable(statement));
    }

    @Test
    void testAddingRemainingAttributes(){
        Statement statement = statementService.createStatement(filename);
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

    /*
    @Test
    void testCreateListJourop(){
        List<String> listeDossier=new ArrayList<>();
        listeDossier.add(filename);
        listeDossier.add(filename2);
        listeDossier.add(filename3);
        listeDossier.add(filename4);
        listeDossier.add(filename5);
        listeDossier.add("invcah_je_ne_suis_pas_un_etat_utilisable.fic");
        listeDossier.add(filename6);


    }
    */

    /*
    @Test
    void testCreateStatementFromFilename(){
        Statement statement = statementService.createStatement(filename);
    }
    */
}
