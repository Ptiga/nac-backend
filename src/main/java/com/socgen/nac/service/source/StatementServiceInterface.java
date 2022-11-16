package com.socgen.nac.service.source;

import com.socgen.nac.entity.source.Statement;
import com.socgen.nac.repository.file.SourceFileRepositoryInterface;

import java.util.List;

public interface StatementServiceInterface {

    void setSourceFileRepository(SourceFileRepositoryInterface sourceFileRepository);

    List<Statement> getUsableStatementsList();

    boolean isExpectedStatementType(Statement statement);

    boolean isExpectedFormat(Statement statement);

    boolean isStatementUsable(Statement statement);

    boolean isInsideUploadedList(Statement statement, List<Statement> uploadedStatements);

    void addRemainingAttributes(Statement statement);

    void manageListOfFunds(List<Statement> listeFichiers, List<Statement> uploadedStatements);

    void splitToDedicatedList(List<Statement> listeFichiers);

    void putStatementInProperList(Statement statement);

    List<Statement> getDedicatedList(String listName);

    List<String[]> createStatementDetail(List<Statement> listStatement);

    void checkSourceFolder();

    List<Statement> uploadStatements();

    int loadAndSaveStatements();

    int checkNewStatements();

    int saveNewStatements();
}


