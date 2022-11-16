package com.socgen.nac.entity.source;

import com.socgen.nac.entity.source.Statement;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class StatementTest {

    String filename = "jourop_kdja_FD0004_20220629_300622091342.fic";

    @Test
    public void testNumberOfSeparators(){
        Statement statement = new Statement(filename, '_', 4);
        Assertions.assertEquals(statement.getNumberOfSeparatorExpected(), statement.getNumberOfSeparators());
    }

}
