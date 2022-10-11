package com.socgen.nac.service.source;

import com.socgen.nac.entity.source.Statement;
import com.socgen.nac.repository.file.SourceFileRepositoryInterface;

import java.util.List;

public interface StatementServiceInterface {

    void setSourceFileRepository(SourceFileRepositoryInterface sourceFileRepository);

    //Statement createStatement(String filename);

    boolean isExpectedStatementType(Statement statement);

    boolean isExpectedFormat(Statement statement);

    boolean isStatementUsable(Statement statement);

    void addRemainingAttributes(Statement statement);

    List<Statement> manageListOfFunds(List<Statement> listeFichiers);

    void splitToDedicatedList(List<Statement> listeFichiers);

    void putStatementInProperList(Statement statement);

    List<Statement> getDedicatedList(String listName);

    void createStatementDetail(List<Statement> listStatement);

    List<Statement> checkSourceFolder();
}


