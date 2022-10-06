package com.socgen.nac.service;

import com.socgen.nac.entity.Statement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StatementServiceTest {

    String filename = "jourop_kdja_FD0004_20220629_300622091342.fic";

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
}
