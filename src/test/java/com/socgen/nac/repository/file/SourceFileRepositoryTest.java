package com.socgen.nac.repository.file;

import com.socgen.nac.entity.source.Statement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

public class SourceFileRepositoryTest {

    SourceFileRepository sourceFileRepository = new SourceFileRepository();

    String sourceFolder = "c//temp//GP3_files_test//";

    @Test
    public void listFolderFiles(){
        List<Statement> listeFichiers = sourceFileRepository.listFiles();
        System.out.println(listeFichiers);
        Assertions.assertEquals(30, listeFichiers.size());
    }
}
