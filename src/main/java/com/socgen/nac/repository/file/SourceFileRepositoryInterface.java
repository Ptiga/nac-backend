package com.socgen.nac.repository.file;

import com.socgen.nac.entity.source.Statement;

import java.util.List;

public interface SourceFileRepositoryInterface {

    List<Statement> listFiles();

    void readSourceFile(Statement statement);

    List<String[]> getExtractedLinesList();

}
