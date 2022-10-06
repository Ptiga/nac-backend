package com.socgen.nac.service;

import com.socgen.nac.entity.Statement;

public interface StatementServiceInterface {

    Statement createStatement(String filename);

    boolean isExpectedStatementType(Statement statement);

    boolean isExpectedFormat(Statement statement);

    boolean isStatementUsable(Statement statement);
}
