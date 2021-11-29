package com.mutants.mutants.service.implTest;

import com.mutants.mutants.model.ModelDNA;
import com.mutants.mutants.model.Persons;
import com.mutants.mutants.repository.PersonsRepository;
import com.mutants.mutants.service.impl.MutantsServiceImpl;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
public class MutantsServiceImplTest {

    public static final String[] DNA_MUTANT = new String[] {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCCCTA","TCACTG"};
    public static final String[] DNA_HUMAN = new String[] {"ATGCGA","CTGTGC","TTATGT","AGAAGG","CCTCTA","TCACTG"};
    public static final String[] DNA_INVALID_DATA = new String[] {"ATGCGA","CAGTGC","TTATGT","AGAAGG","CCOCTA","TCACTG"};

    @Mock
    PersonsRepository personsRepository;

    @InjectMocks
    MutantsServiceImpl mutantsService;

    @Test
    public void verifyADNTestMutant() throws Exception {
        Mockito.when(personsRepository.save(Mockito.any(Persons.class)))
                .thenReturn(Mockito.any(Persons.class));

        ModelDNA modelDNA = new ModelDNA(DNA_MUTANT);
        ResponseEntity serviceResponse = mutantsService.verifyADN(modelDNA);

        Assert.assertEquals(200, serviceResponse.getStatusCodeValue());
    }

    @Test
    public void verifyADNTestHuman() throws Exception {
        Mockito.when(personsRepository.save(Mockito.any(Persons.class)))
                .thenReturn(Mockito.any(Persons.class));

        ModelDNA modelDNA = new ModelDNA(DNA_HUMAN);
        ResponseEntity serviceResponse = mutantsService.verifyADN(modelDNA);

        Assert.assertEquals(403, serviceResponse.getStatusCodeValue());
    }

    @Test
    public void verifyADNTestInvalidData() throws Exception {

        ModelDNA modelDNA = new ModelDNA(DNA_INVALID_DATA);
        ResponseEntity serviceResponse = mutantsService.verifyADN(modelDNA);

        Assert.assertEquals(422, serviceResponse.getStatusCodeValue());
    }

    @Test
    public void verifyADNTestValidDataAlreadyInDatabase() throws Exception {
        Mockito.when(personsRepository.save(Mockito.any(Persons.class)))
                .thenThrow(new IllegalArgumentException());
        ModelDNA modelDNA = new ModelDNA(DNA_MUTANT);
        mutantsService.verifyADN(modelDNA);
    }



    @Test
    public void consultStatsOk() {
        Mockito.when(personsRepository.countMutant()).thenReturn(2L);
        Mockito.when(personsRepository.countNotMutant()).thenReturn(5L);

        ResponseEntity<Map<String, Object>> serviceResponse = mutantsService.consultStats();
        Map<String, Object> data = serviceResponse.getBody();
        Assert.assertEquals(2L, data.get("count_mutant_dna"));
        Assert.assertEquals(5L, data.get("count_human_dna"));
        Assert.assertEquals(2f/5f, data.get("ratio"));
    }

    @Test
    public void consultStatsCaseZero() {
        Mockito.when(personsRepository.countMutant()).thenReturn(2L);
        Mockito.when(personsRepository.countNotMutant()).thenReturn(0L);

        ResponseEntity<Map<String, Object>> serviceResponse = mutantsService.consultStats();
        Map<String, Object> data = serviceResponse.getBody();
        Assert.assertEquals(2L, data.get("count_mutant_dna"));
        Assert.assertEquals(0L, data.get("count_human_dna"));
        Assert.assertEquals(2f, data.get("ratio"));
    }

}
