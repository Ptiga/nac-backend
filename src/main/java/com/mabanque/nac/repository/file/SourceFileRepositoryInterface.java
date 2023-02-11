package com.mabanque.nac.repository.file;

import com.mabanque.nac.entity.source.Statement;

import java.util.List;

public interface SourceFileRepositoryInterface {

    List<Statement> listFiles();

    List<String[]> readSourceFile(Statement statement);

}
