package com.mabanque.nac.repository.file;

import com.mabanque.nac.entity.source.Statement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

//@Repository
public class SourceFileRepositoryTest {

    SourceFileRepository sourceFileRepository = new SourceFileRepository("c://temp//GP3_files_test_class//", '-', 4);

    //String sourceFolder = "c//temp//GP3_files_test//";

    String filename = "invcah_kdja_FD0004_20220630_010722105654.fic";

    @Test
    public void listFolderFiles(){
        List<Statement> listOfFiles = sourceFileRepository.listFiles();
        System.out.println(listOfFiles);
        Assertions.assertEquals(30, listOfFiles.size());
    }
/*
    @Test
    public void getExtractedList(){
        List<String[]>extractedList = sourceFileRepository.getExtractedLinesList();
        Assertions.assertNotNull(extractedList);
    }
*/
    @Test
    public void readInvcahSourceFile(){
        //Repasser en new Statement sans passer par la méthode create
        List<String[]>extractedList = sourceFileRepository.readSourceFile(new Statement(filename, '_', 4));
        Assertions.assertEquals(95, extractedList.size());
    }

    @Test
    public void readJouropSourceFile(){
        List<String[]>extractedList = sourceFileRepository.readSourceFile(new Statement("jourop_kdja_FD0004_20220630_010722105654.fic", '_', 4));
        Assertions.assertEquals(25, extractedList.size());
    }

}
