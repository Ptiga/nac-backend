package com.socgen.nac.entity.result;

import com.socgen.nac.entity.source.Invcah;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class ResultTest {

    @Test
    public void testConvertion(){
        Result result = new Result(new Invcah("x", "x", "x", "x", "x", "x", "x", "x", "x", "x", 1.0, "x"), 0.122734545487, 0.05, "x");
        Assertions.assertEquals(12.27, result.getFluctuation());
        Assertions.assertEquals(5.0, result.getThreshold());
    }
}
