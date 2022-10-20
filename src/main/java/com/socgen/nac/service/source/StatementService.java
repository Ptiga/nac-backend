package com.socgen.nac.service.source;

import com.socgen.nac.entity.source.*;
import com.socgen.nac.repository.database.StatementRepositoryInterface;
import com.socgen.nac.repository.database.ThresholdRepositoryInterface;
import com.socgen.nac.repository.file.SourceFileRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
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
    private InvcahServiceInterface invcahService;

    public InvcahServiceInterface getInvcahService() {
        return invcahService;
    }

    public void setInvcahService(InvcahServiceInterface invcahService) {
        this.invcahService = invcahService;
    }

    @Autowired
    private VinvcaServiceInterface vinvcaService;

    public VinvcaServiceInterface getVinvcaService() {
        return vinvcaService;
    }

    public void setVinvcaService(VinvcaServiceInterface vinvcaService) {
        this.vinvcaService = vinvcaService;
    }

    @Autowired
    private JouropServiceInterface jouropService;

    public JouropServiceInterface getJouropService() {
        return jouropService;
    }

    public void setJouropService(JouropServiceInterface jouropService) {
        this.jouropService = jouropService;
    }

    @Autowired
    private StatementRepositoryInterface statementRepository;

    public StatementRepositoryInterface getStatementRepository() {
        return statementRepository;
    }

    public void setStatementRepository(StatementRepositoryInterface statementRepository) {
        this.statementRepository = statementRepository;
    }

    public StatementService(SourceFileRepositoryInterface sourceFileRepository, InvcahServiceInterface invcahService, VinvcaServiceInterface vinvcaService, JouropServiceInterface jouropService, StatementRepositoryInterface statementRepository) {
        this.sourceFileRepository = sourceFileRepository;
        this.invcahService = invcahService;
        this.vinvcaService = vinvcaService;
        this.jouropService = jouropService;
        this.statementRepository = statementRepository;
    }




    @Override
    public boolean isExpectedStatementType(Statement statement) {
        switch (statement.getStatementType()){
            case "invcah":
            case "vinvca":
            case "jourop" :
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
    public void manageListOfFunds(List<Statement> listeFichiers, List<Statement> uploadedStatements) {
        usableStatementsList.clear();
        for (Statement statement: listeFichiers) {
            if(isStatementUsable(statement) && !isInsideUploadedList(statement, uploadedStatements)){
                addRemainingAttributes(statement);
                usableStatementsList.add(statement);
            }
        }
    }

    private List<Statement> compareStatements(List<Statement> statements, List<Statement> uploadedStatements) {
        List<Statement>returnedList = new ArrayList<>();
        for (Statement statement: statements) {
            if(!isInsideUploadedList(statement, uploadedStatements) && isStatementUsable(statement)){
                addRemainingAttributes(statement);
                returnedList.add(statement);
            }
        }
        return returnedList;
    }

    @Override
    public boolean isInsideUploadedList(Statement statement, List<Statement> uploadedStatements){
        for (Statement uploadedStatement: uploadedStatements) {
            if (uploadedStatement.getFilename().equals(statement.getFilename())){
                return true;
            }
        }
        return false;
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
    public List<Statement> uploadStatements(){
        List<Statement>uploadedStatements = new ArrayList<>();
        Iterable<Statement> statementsFromDatabase = statementRepository.findAll();
        statementsFromDatabase.forEach(uploadedStatements::add);
        return uploadedStatements;
    }

    private void saveStatementsToDatabase(List<Statement> statements, List<Invcah> invcahs, List<Vinvca> vinvcas, List<Jourop> jourops){
        /*
        for (Statement statement: statements) {
            statementRepository.save(statement);
        }
         */
        statementRepository.saveAll(statements);
        invcahService.saveInvcahData(invcahs);
        vinvcaService.saveVinvcaData(vinvcas);
        jouropService.saveJouropData(jourops);
    }








    @Override
    public int loadAndSaveStatements() {
        //On charge la liste des fichiers déjà présents en base
        List<Statement>uploadedStatements = uploadStatements();
        //Liste des fichiers présents dans le dossier source
        List<Statement>statements = sourceFileRepository.listFiles();
        //On vérifie si les états sont corrects puis on ajoute les attributs manquants
        manageListOfFunds(statements, uploadedStatements);
        //On sauvegarde les états dans la BDD
        //saveStatementsToDatabase(usableStatementsList);

        return usableStatementsList.size();
    }

    //Méthode pour vérifier s'il y a de nouveaux états
    @Override
    public int checkNewStatements() {
        //On charge la liste des fichiers déjà présents en base
        List<Statement>uploadedStatements = uploadStatements();
        //Liste des fichiers présents dans le dossier source
        List<Statement>statements = sourceFileRepository.listFiles();
        statements = compareStatements(statements, uploadedStatements);
        return statements.size();
    }

    @Override
    public int saveNewStatements() {
        if(checkNewStatements()==0){
            return 0;
        }else{
            //On charge la liste des fichiers déjà présents en base
            List<Statement>uploadedStatements = uploadStatements();
            //Liste des fichiers présents dans le dossier source
            List<Statement>statements = sourceFileRepository.listFiles();
            //On vérifie si les états sont corrects puis on ajoute les attributs manquants
            statements = compareStatements(statements, uploadedStatements);
            splitToDedicatedList(statements);
            List<Invcah>invcahs = invcahService.createInvcahAndAddToList(createStatementDetail(listeInvcah));
            List<Vinvca>vinvcas = vinvcaService.createVinvcaAndAddToList(createStatementDetail(listeVinvca));
            List<Jourop>jourops = jouropService.createJouropAndAddToList(createStatementDetail(listeJourop));
            saveStatementsToDatabase(statements, invcahs, vinvcas, jourops);
            return statements.size();
        }
    }



}
