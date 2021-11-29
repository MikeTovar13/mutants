package com.mutants.mutants.logicTest;

import com.mutants.mutants.logic.DNA;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Arrays;

@RunWith(SpringJUnit4ClassRunner.class)
public class MutantsLogicDNATest {

    private static final  String MUTANT_OPTION = "1";
    private static final  String WRONG_DNA_OPTION = "2";
    private DNA dna;

    @Rule
    public ExpectedException exceptionRule = ExpectedException.none();


    private static Iterable dataMutant(String option){
        if (option== MUTANT_OPTION)
            return Arrays.asList(
                    new Object[][]{
                            {new String[]{"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"}, true},
                            {new String[]{"ATGCGA","CTGTGC","TTATGT","AGAAGG","CCGCTA","TCACTG"}, false}
                    }
            );
        else {
            return Arrays.asList(
                    new Object[][]{
                            {new String[]{"ATGCG","CAGTG","TTATT","AGAAG","CCCTA","TCACG"}, "Not Valid DNA Strand"},
                            {new String[]{"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCOTA","TCACTG"}, "Not Valid DNA Strand"}
                    }
            );
        }
    }

    @Test
    public void TestExceptionOnWrongDNA() throws Exception {
        for (Object obj: dataMutant(WRONG_DNA_OPTION)){
            Object[] data = (Object[])obj;
            String[] initial_dna = (String[])data[0];
            String err_msj = (String) data[1];
            dna = new DNA();
            exceptionRule.expect(Exception.class);
            exceptionRule.expectMessage(err_msj);
            dna.isMutant(initial_dna);
        }
    }

    @Test
    public void TestIsMutantDNA() throws Exception {
        for (Object obj: dataMutant(MUTANT_OPTION)){
            Object[] data = (Object[])obj;
            String[] initial_dna = (String[])data[0];
            boolean is_mutant = (boolean)data[1];
            dna = new DNA();
            Assert.assertEquals(is_mutant, dna.isMutant(initial_dna));
        }
    }


}
