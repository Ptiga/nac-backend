package com.socgen.nac.repository.file;

import com.socgen.nac.entity.source.Statement;
import com.socgen.nac.repository.database.ThresholdRepositoryInterface;
import com.socgen.nac.service.source.StatementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class SourceFileRepository implements SourceFileRepositoryInterface {

    private String sourceFolder;
    private Character dataSeparator;
    private int numberOfSeparatorExpected;


    public SourceFileRepository(@Value("${statement.directory}")String sourceFolder, @Value("${statement.dataSeparator}")Character dataSeparator, @Value("${statementService.numberOfSeparatorExpected}")int numberOfSeparatorExpected) {
        this.sourceFolder = sourceFolder;
        this.dataSeparator = dataSeparator;
        this.numberOfSeparatorExpected = numberOfSeparatorExpected;
    }
/*
    public List<Statement> getListOfFiles() {
        return listOfFiles;
    }
*/
    @Override
    public List<Statement> listFiles() {
        List<Statement> listOfFiles = new ArrayList<>();
        File dir  = new File(sourceFolder);
        File[] liste = dir.listFiles();
        for(File item : liste){
            if(item.isFile())
            {
                //listFiles.add(statementService.createStatement(item.getName()));
                listOfFiles.add(new Statement(item.getName(), this.dataSeparator, this.numberOfSeparatorExpected));
            }
        }
        return listOfFiles;
    }

    @Override
    public List<String[]> readSourceFile(Statement statement) {
        List<String[]> extractedLines = new ArrayList<>();
        try {
            int lineNum = 1;
            FileReader fileReader = new FileReader(sourceFolder + statement.getFilename());
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String line = bufferedReader.readLine();

            while (line != null) {
            if((statement.isStatementContainsHeader() && lineNum!=1) || !statement.isStatementContainsHeader()){
                String[] ligneFichier = (statement.getFilename() + ";" + line).split(";");
                extractedLines.add(ligneFichier);
            }
            line = bufferedReader.readLine();
            lineNum++;
            }
            bufferedReader.close();
        } catch (Exception e) {
            throw new Error(e);
        }
        return extractedLines;
    }


    /*
    @Override
    public List<String[]> getExtractedLinesList() {
        return extractedLines;
    }
    */



//Interface avec BDD ou répertoire

}
