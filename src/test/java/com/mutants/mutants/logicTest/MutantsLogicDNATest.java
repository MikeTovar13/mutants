package com.mutants.mutants.logicTest;

import com.mutants.mutants.logic.DNA;
import com.mutants.mutants.logic.Matrix;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
public class MutantsLogicDNATest {

    private DNA dna;

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();

    @Test
    public void TestExceptionOnWrongDNA() throws Exception {
        String[] initialDNA = new String[] {"","","",""};

        dna = new DNA();
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("Not Valid DNA Strand");
        dna.isMutant(initialDNA);


    }
    @Test
    public void TestExceptionOnWrongDataInDNA() throws Exception {
        String[] initialDNA = new String[] {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCOTA","TCACTG"};
        dna = new DNA();
        exceptionRule.expect(Exception.class);
        exceptionRule.expectMessage("Not Valid DNA Strand");
        dna.isMutant(initialDNA);

    }


    @Test
    public void TestIsMutantDNA() throws Exception {
        String[] initialDNA = new String[] {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
        dna = new DNA();
        Assert.assertEquals(true, dna.isMutant(initialDNA));

    }

    @Test
    public void TestIsNotMutantDNA() throws Exception {
        String[] initialDNA = new String[] {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCGCTA","TCACTG"};
        dna = new DNA();
        Assert.assertEquals(false, dna.isMutant(initialDNA));

    }

}
