package com.socgen.nac.service.source;

import com.socgen.nac.entity.source.Statement;

public interface StatementServiceInterface {

    Statement createStatement(String filename);

    boolean isExpectedStatementType(Statement statement);

    boolean isExpectedFormat(Statement statement);

    boolean isStatementUsable(Statement statement);

    void addRemainingAttributes(Statement statement);
}

