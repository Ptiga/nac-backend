package com.socgen.nac.service.source;

import com.socgen.nac.entity.source.Statement;
import com.socgen.nac.repository.database.StatementRepositoryInterface;
import com.socgen.nac.repository.database.ThresholdRepositoryInterface;
import com.socgen.nac.repository.file.SourceFileRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatementService implements StatementServiceInterface{

    private List<Statement> usableStatementsList = new ArrayList<>();
    private List<Statement>listeInvcah = new ArrayList<>();
    private List<Statement>listeVinvca = new ArrayList<>();
    private List<Statement>listeJourop = new ArrayList<>();

    public List<Statement> getUsableStatementsList() {
        return usableStatementsList;
    }

    @Autowired
    private SourceFileRepositoryInterface sourceFileRepository;


    public SourceFileRepositoryInterface getSourceFileRepository() {
        return sourceFileRepository;
    }

    public void setSourceFileRepository(SourceFileRepositoryInterface sourceFileRepository) {
        this.sourceFileRepository = sourceFileRepository;
    }

    @Autowired
    private StatementRepositoryInterface statementRepository;

    public StatementRepositoryInterface getStatementRepository() {
        return statementRepository;
    }

    public void setStatementRepository(StatementRepositoryInterface statementRepository) {
        this.statementRepository = statementRepository;
    }

    public StatementService(SourceFileRepositoryInterface sourceFileRepository, StatementRepositoryInterface statementRepository) {
        this.sourceFileRepository = sourceFileRepository;
        this.statementRepository = statementRepository;
    }




    @Override
    public boolean isExpectedStatementType(Statement statement) {
        switch (statement.getStatementType()){
            case "invcah", "vinvca", "jourop" :
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
        if (isExpectedStatementType(statement) && isExpectedFormat(statement) && statement.getNumberOfSeparators()==statement.getNumberOfSeparatorExpected()){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public void manageListOfFunds(List<Statement> listeFichiers) {
        usableStatementsList.clear();
        for (Statement statement: listeFichiers) {
            if(isStatementUsable(statement)){
                addRemainingAttributes(statement);
                usableStatementsList.add(statement);
            }
        }
    }

    @Override
    public void addRemainingAttributes(Statement statement) {
        List<String> listAttributes = new ArrayList<String>();
        String filename = statement.getFilename().substring(statement.getFilename().indexOf(statement.getDataSeparator())+1);
        for(int i = 1; i < statement.getNumberOfSeparators(); i++){
            listAttributes.add(filename.substring(0, filename.indexOf(statement.getDataSeparator())));
            filename = filename.substring(filename.indexOf(statement.getDataSeparator())+1);
        }
        listAttributes.add(filename.substring(0, filename.indexOf('.')));
        statement.setUserTag(listAttributes.get(0));
        statement.setFund(listAttributes.get(1));
        statement.setNavDate(listAttributes.get(2));
        statement.setFileTimestamp(listAttributes.get(3));
    }

    @Override
    public void splitToDedicatedList(List<Statement> listeFichiers) {
        listeInvcah.clear();
        listeVinvca.clear();
        listeJourop.clear();
        for (Statement statement: listeFichiers) {
            putStatementInProperList(statement);
        }
    }

    @Override
    public void putStatementInProperList(Statement statement) {
        switch (statement.getStatementType()){
            case "invcah":
                listeInvcah.add(statement);
                break;
            case "vinvca":
                listeVinvca.add(statement);
                break;
            case "jourop":
                listeJourop.add(statement);
                break;
            default:
                break;
        }
    }

    @Override
    public List<Statement> getDedicatedList(String listName) {
        switch (listName){
            case "invcah":
                return listeInvcah;
            case "vinvca":
                return listeVinvca;
            case "jourop":
                return listeJourop;
            default:
                return null;
        }
    }

    @Override
    public List<String[]> createStatementDetail(List<Statement> listStatement) {
        List<String[]>extractedList = new ArrayList<>();
        for (Statement statement: listStatement) {
            //sourceFileRepository = getSourceFileRepository();
            //extractedList = sourceFileRepository.readSourceFile(statement);
            extractedList.addAll(sourceFileRepository.readSourceFile(statement));
        }
        return extractedList;
    }

    @Override
    public void checkSourceFolder() {
        sourceFileRepository.listFiles();
    }

    @Override
    public void loadAndSaveStatements() {
        //Liste des fichiers présents dans le dossier source
        List<Statement>statements = sourceFileRepository.listFiles();
        //On vérifie si les états sont corrects puis on ajoute les attributs manquants
        manageListOfFunds(statements);
        //On sauvegarde les états dans la BDD
        saveStatementsToDatabase(statements);
    }

    private void saveStatementsToDatabase(List<Statement> statements){
        for (Statement statement: statements) {
            statementRepository.save(statement);
        }
    }


    //Appel dans le repo
}
