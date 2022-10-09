package com.socgen.nac.repository.file;

import com.socgen.nac.entity.source.Statement;
import com.socgen.nac.service.source.StatementService;
import org.springframework.stereotype.Repository;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@Repository
public class SourceFileRepository implements SourceFileRepositoryInterface {

    StatementService statementService = new StatementService();

    /*
    @Value("${statement.directory}")
    private String sourceFolder;
    */

    private String sourceFolder = "c://temp//GP3_files_test//";


    @Override
    public List<Statement> listFiles() {
        List <Statement> listFiles = new ArrayList<>();
        File dir  = new File(sourceFolder);
        File[] liste = dir.listFiles();
        for(File item : liste){
            if(item.isFile())
            {
                listFiles.add(statementService.createStatement(item.getName()));
            }
            /*
            else if(item.isDirectory())
            {
                System.out.format("Nom du répertoir: %s%n", item.getName());
            }

             */
        }
        return listFiles;
    }


    //Interface avec BDD ou répertoire

}
