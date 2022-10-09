package com.socgen.nac.service.source;

import com.socgen.nac.entity.source.Statement;
import com.socgen.nac.repository.file.SourceFileRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatementService implements StatementServiceInterface{

    @Value("${statementService.numberOfSeparatorExpected}")
    private int numberOfSeparatorExpected;


    @Autowired
    private SourceFileRepositoryInterface statementRepository;


    public SourceFileRepositoryInterface getStatementRepository() {
        return statementRepository;
    }

    public void setStatementRepository(SourceFileRepositoryInterface statementRepository) {
        this.statementRepository = statementRepository;
    }


    @Override
    public Statement createStatement(String filename) {
        return new Statement(filename);
    }

    @Override
    public boolean isExpectedStatementType(Statement statement) {
        switch (statement.getStatementType()){
            case "invcah", "vinvcah", "jourop" :
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean isExpectedFormat(Statement statement) {
        switch (statement.getFileExtension()){
            case "fic" :
                return true;
            default:
                return false;
        }
    }

    @Override
    public boolean isStatementUsable(Statement statement) {
        //if (isExpectedStatementType(statement) && isExpectedFormat(statement) && statement.getNumberOfSeparators()==numberOfSeparatorExpected){
        if (isExpectedStatementType(statement) && isExpectedFormat(statement) && statement.getNumberOfSeparators()==4){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void addRemainingAttributes(Statement statement) {
        List<String> listAttributes = new ArrayList<String>();
        String filename = statement.getFilename().substring(statement.getFilename().indexOf('_')+1);
        for(int i = 1; i < statement.getNumberOfSeparators(); i++){
            listAttributes.add(filename.substring(0, filename.indexOf('_')));
            filename = filename.substring(filename.indexOf('_')+1);
        }
        listAttributes.add(filename.substring(0, filename.indexOf('.')));
        statement.setUserTag(listAttributes.get(0));
        statement.setFund(listAttributes.get(1));
        statement.setNavDate(listAttributes.get(2));
        statement.setFileTimestamp(listAttributes.get(3));
    }


    //Appel dans le repo

}
