package com.socgen.nac.service;

import com.socgen.nac.entity.Statement;
import com.socgen.nac.repository.SourceFileRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StatementService implements StatementServiceInterface{

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
        if (isExpectedStatementType(statement) && isExpectedFormat(statement) && statement.getNumberOfSeparators()==4){
            return true;
        }else{
            return false;
        }
    }




    //Appel dans le repo

}
